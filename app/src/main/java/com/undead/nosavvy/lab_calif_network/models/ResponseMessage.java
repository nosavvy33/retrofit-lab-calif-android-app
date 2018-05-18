package com.undead.nosavvy.lab_calif_network.models;

/**
 * Created by nosavvy on 5/17/18.
 */

public class ResponseMessage {
    public ResponseMessage(String message, String type) {
        this.message = message;
        this.type = type;
    }

    private String message;
    private String type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
