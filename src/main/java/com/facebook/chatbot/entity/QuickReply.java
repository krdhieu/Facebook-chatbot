package com.facebook.chatbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
@JsonIgnoreProperties(ignoreUnknown = true)
//@Component
public class QuickReply {
    String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "QuickReply{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
