package com.codeusingjava.model;

public class TextGenerationRequest {
    private String text;

    public TextGenerationRequest() {}

    public TextGenerationRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}