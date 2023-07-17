package com.facebook.chatbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
@JsonIgnoreProperties(ignoreUnknown = true)
//@Component
public class Recipient {
    String id;

    public Recipient(String id) {
        this.id = id;
    }

    public Recipient() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "id='" + id + '\'' +
                '}';
    }
}
