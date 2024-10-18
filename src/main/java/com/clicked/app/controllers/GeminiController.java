package com.clicked.app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.clicked.app.dto.ApiResponse;
import com.clicked.app.dto.geminidto.CandidateDto;
import com.clicked.app.dto.geminidto.DataDto;
import com.clicked.app.dto.geminidto.QuestionRequestDto;
import com.clicked.app.services.gemini.IGeminiService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class GeminiController {

  public static final String BASE_PATH = "api/v1";

  private IGeminiService geminiService;

  public GeminiController(IGeminiService geminiService) {
    this.geminiService = geminiService;
  }

  @PostMapping(value = BASE_PATH + "/gemini/ask")
  public ResponseEntity<ApiResponse<?>> askGemini(@RequestBody QuestionRequestDto request) {
    DataDto response = geminiService.askGemini(request);

    CandidateDto[] candidates = response.getCandidates();


    if (candidates.length > 0) {
      return ResponseEntity.status(200).body(
        new ApiResponse<String>(
          "ok",
          "Gemini's Answer",
          candidates[0].getContent().getParts().get(0).getText()
        )
      );
    }

    return ResponseEntity.status(403).body(
      new ApiResponse<String>(
        "err",
        "Oops! your query is not procesed",
        "Something went wrong!"
      )
    );
  }
  
}
