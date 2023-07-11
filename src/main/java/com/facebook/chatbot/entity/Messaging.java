package com.facebook.chatbot.entity;

public class Messaging {
    Sender sender;
    Recipient recipient;

    public Messaging(Sender sender, Recipient recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}
