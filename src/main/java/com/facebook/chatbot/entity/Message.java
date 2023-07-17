package com.facebook.chatbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
@JsonIgnoreProperties(ignoreUnknown = true)
//@Component
public class Message {
    String mid;
    String text;
    QuickReply quick_reply;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mid='" + mid + '\'' +
                ", text='" + text + '\'' +
                ", quick_reply=" + quick_reply +
                '}';
    }
}
