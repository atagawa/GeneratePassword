package com.example;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Messages {
    private ResourceBundle bundle;

    public Messages() {
        this.bundle = ResourceBundle.getBundle("Messages");
    }

    public String getMessage(MessageType mType) {
        return bundle.getString(mType.name());

    }

    public String getMessageWithFormat(MessageType mType, Object... args) {
        return MessageFormat.format(getMessage(mType), args);

    }
}
