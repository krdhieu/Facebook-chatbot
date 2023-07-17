package com.facebook.chatbot.webhooks;

import com.facebook.chatbot.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;


@RestController
public class EndPoint {
    private static final Logger logger = LoggerFactory.getLogger(EndPoint.class);

    ObjectMapper objectMapper = new ObjectMapper();
    @Value("${VERIFY_TOKEN}")
    private String verify_token;

    @Value("${PAGE_ACCESS_TOKEN}")
    private String page_access_token;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebHookPOSTRequest(HttpServletRequest request, @RequestBody WebhookPayload body) throws IOException {
        if (body.getObject().equals("page")) {
            System.out.println("================ into if statement =================");
            body.getEntry().forEach(entry -> {
                System.out.println("========== entry ==========" + entry);
                entry.getMessaging().forEach(message -> {
                    System.out.println(message.getSender().getId());
                    System.out.println(message.getRecipient().getId());
                    logger.info("============ sender id ============ " + message.getSender().getId());
                    if (message != null) {
                        handleMessage(message.getSender().getId(), message.getMessage());
                    } else {
                        System.out.println("no message");
                    }
                });

            });
            return new ResponseEntity<>("EVENT_RECEIVED", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/webhook")
    public ResponseEntity<String> handleWebHookGETRequest(HttpServletRequest request
    ) {
        String mode = request.getParameter("hub.mode");
        String token = request.getParameter("hub.verify_token");
        String challenge = request.getParameter("hub.challenge");
        if (mode != null && token != null) {
            String pageAccessToken = verify_token;
            if (mode.equals("subscribe") && token.equals(pageAccessToken)) {
                System.out.println("WEBHOOK_VERIFIED");
                return new ResponseEntity<>(challenge, HttpStatus.OK);
            } else {
                System.out.println("WEBHOOK_NOT_VERIFIED");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    public void handleMessage(String sender_psid, Message message)  {
        ResponseChatBot reply_mess = new ResponseChatBot();
        System.out.println("================== message text ====================" + message.getText());
        reply_mess.setText("you sent the message: " + message.getText() + ". Now sent image!");
        System.out.println("");
        callSendAPI(sender_psid, reply_mess);
    }

    public void callSendAPI(String sender_psid, ResponseChatBot response)   {
        RequestBodyChatBot requestBodyChatBot = new RequestBodyChatBot(new Sender(sender_psid), response);
        String json = "";
        try {
             json = objectMapper.writeValueAsString(requestBodyChatBot);
            System.out.println("=================json of body ===================" + json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String url = "https://graph.facebook.com/v2.6/me/messages";
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(logRequest())
                .build();

        Mono<String> responseMono = webClient.post()
                .uri(uriBuilder -> uriBuilder.queryParam("access_token", page_access_token).build())
                .body(BodyInserters.fromValue(requestBodyChatBot))
                .retrieve()
                .bodyToMono(String.class);

        responseMono.subscribe(responseBody -> {
            System.out.println("Message sent!");
            System.out.println("Response body: " + responseBody);
        }, error -> {
            System.err.println("Unable to send message: " + error.getMessage());
        });
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }
}




