package com.facebook.chatbot.entity;

public class RequestBodyChatBot {
    Sender recipient;
    ResponseChatBot message;

    public RequestBodyChatBot(Sender sender, ResponseChatBot responseChatBot) {
        this.recipient = sender;
        this.message = responseChatBot;
    }

    public RequestBodyChatBot() {
    }

    public Sender getRecipient() {
        return recipient;
    }

    public void setRecipient(Sender recipient) {
        this.recipient = recipient;
    }

    public ResponseChatBot getMessage() {
        return message;
    }

    public void setMessage(ResponseChatBot message) {
        this.message = message;
    }
}
