package com.clicked.app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.clicked.app.dto.ApiResponse;
import com.clicked.app.dto.ApiResponseMsgOnly;
import com.clicked.app.dto.pricingdto.CreatePricingRequestDto;
import com.clicked.app.dto.pricingdto.PricingDto;
import com.clicked.app.services.pricing.IPricingService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
  public ResponseEntity<ApiResponse<List<PricingDto>>> getAllPricing() {
    List<PricingDto> allPricing = pricingService.getPricing();

    return ResponseEntity.status(200).body(
      new ApiResponse<List<PricingDto>>(
        "ok",
        "Here is your pricing list",
        allPricing
      )
    );
  }

  @PostMapping(value = BASE_PATH + "/pricing/update")
  public ResponseEntity<ApiResponse<PricingDto>> updatePricing(@RequestBody PricingDto request) {
      PricingDto pricing = pricingService.updatePricing(request);

      return ResponseEntity.status(200).body(
      new ApiResponse<PricingDto>(
        "ok",
        "Here is your pricing list",
        pricing
      )
    );
  }
  
  @DeleteMapping(value = BASE_PATH + "/pricing/delete/{id}")
  public ResponseEntity<ApiResponseMsgOnly> deletePricing(@PathVariable Long id) {
    boolean result = pricingService.deletePricing(id);

    if (!result) {
      return ResponseEntity.status(200).body(
        new ApiResponseMsgOnly(
          "err",
          "Oops! An error has occured we cannot delete your pricing"
        )
      );
    }

    return ResponseEntity.status(200).body(
      new ApiResponseMsgOnly(
        "ok",
        "Pricing are successfully deleted"
      )
    );
  }
}
