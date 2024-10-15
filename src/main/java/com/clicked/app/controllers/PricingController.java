package com.clicked.app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.clicked.app.dto.ApiResponse;
import com.clicked.app.dto.ApiResponseMsgOnly;
import com.clicked.app.dto.pricingdto.CreatePricingRequestDto;
import com.clicked.app.dto.pricingdto.GetPricingResponseDto;
import com.clicked.app.services.pricing.IPricingService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class PricingController {

  public static final String BASE_PATH = "api/v1";

  public IPricingService pricingService;

  public PricingController(IPricingService pricingService) {
    this.pricingService = pricingService;
  }

  @PostMapping(value = BASE_PATH + "/pricing/create")
  public ResponseEntity<ApiResponseMsgOnly> createPricingEntity(@RequestBody CreatePricingRequestDto request) {
    boolean result = pricingService.createPricing(request);

    if (!result) {
      return ResponseEntity.status(200).body(
        new ApiResponseMsgOnly(
          "err",
          "Oops! An error has occured"
        )
      );
    }

    return ResponseEntity.status(200).body(
      new ApiResponseMsgOnly(
        "ok",
        "Pricing are successfully created"
      )
    );
  }

  @GetMapping(value = BASE_PATH + "/pricing/all")
  public ResponseEntity<ApiResponse<List<GetPricingResponseDto>>> getMethodName() {
      List<GetPricingResponseDto> allPricing = pricingService.getPricing();

      return ResponseEntity.status(200).body(
        new ApiResponse<List<GetPricingResponseDto>>(
          "ok",
          "Here is your pricing list",
          allPricing
        )
      );
  }
  
  
}
