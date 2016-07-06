package com.zillion.delhibelly.liftsManager.Network.Models;


public class ApiError {

    private String status;
    private String message;
    private String validation_messages[];

    public ApiError() {
    }

    public void setStatus_code(String status_code) {
        this.status = status_code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setValidation_messages(String[] validation_messages) {
        this.validation_messages = validation_messages;
    }

    public String status() {
        return status;
    }
    public String message() {
        return message;
    }
    public String[] validation_messages() {
        return validation_messages;
    }
}
