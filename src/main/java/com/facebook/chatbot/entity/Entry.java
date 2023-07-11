package com.facebook.chatbot.entity;

import java.util.Date;
import java.util.List;

public class Entry {
    String id;
    long time;
    List<Messaging> messagingList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<Messaging> getMessagingList() {
        return messagingList;
    }

    public void setMessagingList(List<Messaging> messagingList) {
        this.messagingList = messagingList;
    }
}
