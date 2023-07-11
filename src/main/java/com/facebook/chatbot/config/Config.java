package com.facebook.chatbot.config;

import org.springframework.stereotype.Component;

@Component
public class Config {
    String verifyToken = "verify_token";
    public String getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
    }
}
