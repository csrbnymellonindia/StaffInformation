package com.codeusingjava.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeusingjava.model.TextGenerationRequest;

import java.io.IOException;
import java.net.http.HttpRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import javax.websocket.Decoder.Text;

@RestController
public class FeedbackAnalysisController {

    private final String apiUrl = "https://api.edenai.run/v2/text/generation";
    private final String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiODRiYzA5NjUtMGRiMi00OGFmLTg5NjEtNjNjZmEyNjY2MzY3IiwidHlwZSI6ImFwaV90b2tlbiJ9.RYU2_icrAgxZ9pWJVJIzo15B-gmOTD-4i1oXBcqCoF0";

    @PostMapping("/generateText")
    
    public String generateText(@RequestBody TextGenerationRequest request) {
        String text = request.getText();
        System.out.println("{\"providers\":\"google\",\"text\":\"" + text+ "\",\"max_tokens\":10,\"temperature\":0}");

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://api.edenai.run/v2/text/generation"))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("authorization",
                        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiODRiYzA5NjUtMGRiMi00OGFmLTg5NjEtNjNjZmEyNjY2MzY3IiwidHlwZSI6ImFwaV90b2tlbiJ9.RYU2_icrAgxZ9pWJVJIzo15B-gmOTD-4i1oXBcqCoF0")
                .method("POST",
                        HttpRequest.BodyPublishers.ofString("{\"providers\":\"openai\",\"text\":\"" + text+ "\",\"max_tokens\":200,\"temperature\":0}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send((java.net.http.HttpRequest) request1,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }
}