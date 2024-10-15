package com.clicked.app.services.pricing;

import java.util.List;

import com.clicked.app.dto.pricingdto.CreatePricingRequestDto;
import com.clicked.app.dto.pricingdto.GetPricingResponseDto;

public interface IPricingService {
  boolean createPricing(CreatePricingRequestDto request);
  List<GetPricingResponseDto> getPricing();
}
