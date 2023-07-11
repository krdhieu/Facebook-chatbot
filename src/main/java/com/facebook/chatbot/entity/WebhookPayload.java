package com.facebook.chatbot.entity;

import java.util.List;

public class WebhookPayload {
    String object;
    List<Entry> entryList;

    public WebhookPayload(String object, List<Entry> entryList) {
        this.object = object;
        this.entryList = entryList;
    }

    public WebhookPayload() {
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}
