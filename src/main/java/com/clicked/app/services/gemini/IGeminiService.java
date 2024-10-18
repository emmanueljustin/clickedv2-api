package com.clicked.app.services.gemini;

import com.clicked.app.dto.geminidto.DataDto;
import com.clicked.app.dto.geminidto.QuestionRequestDto;

public interface IGeminiService {
  DataDto askGemini(QuestionRequestDto request);
}
