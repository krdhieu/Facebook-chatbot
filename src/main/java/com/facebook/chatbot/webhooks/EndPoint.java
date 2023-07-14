package com.facebook.chatbot.webhooks;

import com.facebook.chatbot.config.Config;
import com.facebook.chatbot.entity.WebhookPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@RestController
public class EndPoint {

    @Value("${VERIFY_TOKEN}")
    String verify_token;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebHookPOSTRequest(@RequestBody WebhookPayload body) {
        System.out.println("\\u{1F7EA} Received webhook:");
        System.out.println(body);
        System.out.println(body.getObject());
        if(body.getObject().equals("page")) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/messaging-webhook")
    public ResponseEntity<String> handleWebHookGETRequest(HttpServletRequest request
    ) {
        String mode = request.getParameter("hub.mode");
        String token = request.getParameter("hub.verify_token");
        String challenge = request.getParameter("hub.challenge");
        if(mode != null && token != null) {
            String pageAccessToken = verify_token;
            if(mode.equals("subscribe") && token.equals(pageAccessToken) ) {
                System.out.println("WEBHOOK_VERIFIED");
                return new ResponseEntity<>( challenge, HttpStatus.OK);
            } else  {
                System.out.println("WEBHOOK_NOT_VERIFIED");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
