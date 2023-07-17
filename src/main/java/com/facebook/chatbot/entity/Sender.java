package com.facebook.chatbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
@JsonIgnoreProperties(ignoreUnknown = true)
//@Component
public class Sender {
    String id;

    public Sender(String id) {
        this.id = id;
    }

    public Sender() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "id='" + id + '\'' +
                '}';
    }
}
