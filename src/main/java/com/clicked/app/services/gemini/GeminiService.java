package com.clicked.app.services.gemini;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clicked.app.dto.geminidto.DataDto;
import com.clicked.app.dto.geminidto.QuestionRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class GeminiService implements IGeminiService {
  
  private RestTemplate restTemplate;

  @Value("${gemini.api.url}")
  private String API_URL;

  @Value("${gemini.api.key}")
  private String KEY;

  public GeminiService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public DataDto askGemini(QuestionRequestDto request) {
    String geminiApi = String.format(API_URL, KEY);

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode contentNode = objectMapper.createObjectNode();
    ObjectNode partsNode = objectMapper.createObjectNode();
    partsNode.put("text", request.getQuestion());
    contentNode.set("parts", objectMapper.createArrayNode().add(partsNode));
    ObjectNode requestBodyNode = objectMapper.createObjectNode();
    requestBodyNode.set("contents", objectMapper.createArrayNode().add(contentNode));
    
    String requestBody;
    try {
        requestBody = objectMapper.writeValueAsString(requestBodyNode);
    } catch (Exception e) {
        throw new RuntimeException("Failed to construct JSON request body", e);
    }

    HttpEntity<String> geminiRequest = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange(geminiApi, HttpMethod.POST, geminiRequest, String.class);

    DataDto responseDto;
    try {
        responseDto = objectMapper.readValue(response.getBody(), DataDto.class);
    } catch (Exception e) {
        throw new RuntimeException("Failed to deserialize response from Gemini API", e);
    }

    return responseDto;
  }
}
