package com.facebook.chatbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
//@Component
public class WebhookPayload {
    String object;
    List<Entry> entry;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "WebhookPayload{" +
                "object='" + object + '\'' +
                ", entry=" + entry +
                '}';
    }
}
