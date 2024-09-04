package com.example.exception;

public class CustomException extends RuntimeException {
    private String statusText;

    public CustomException(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
