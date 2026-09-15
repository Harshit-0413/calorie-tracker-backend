package com.calorietracker.client;

import com.calorietracker.dto.ClaudeMessage;
import com.calorietracker.dto.ClaudeRequest;
import com.calorietracker.dto.ClaudeResponse;
import com.calorietracker.exception.ClaudeApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.JsonNode;

import java.util.List;

@Component
public class ClaudeClient {

    private final RestClient restClient;
    private final String apiKey;
    private final ObjectMapper objectMapper;

    public ClaudeClient(RestClient restClient, @Value("${anthropic.api-key}") String apiKey, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.apiKey = apiKey;
        this.objectMapper = objectMapper;
    }

    public String testRequest(String prompt) {

        ClaudeMessage message = new ClaudeMessage("user", prompt);

        ClaudeRequest request = new ClaudeRequest();

        request.setModel("claude-haiku-4-5-20251001");
        request.setMax_tokens(500);
        request.setMessages(List.of(message));

        String responseBody = restClient.post().uri("https://api.anthropic.com/v1/messages").header("x-api-key", apiKey).header("anthropic-version", "2023-06-01").header("content-type", "application/json").body(request).retrieve().onStatus(status -> status.is4xxClientError(), (req, response) -> {

            String errorBody = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);

            JsonNode json = objectMapper.readTree(errorBody);

            String errorMessage = json.path("error").path("message").asText();

            throw new ClaudeApiException(errorMessage);
        }).body(String.class);

        ClaudeResponse response = objectMapper.readValue(responseBody, ClaudeResponse.class);

        return response.getContent().get(0).getText();


    }
}
