package com.clicked.app.services.pricing;

import java.util.List;

import com.clicked.app.dto.pricingdto.CreatePricingRequestDto;
import com.clicked.app.dto.pricingdto.PricingDto;

public interface IPricingService {
  boolean createPricing(CreatePricingRequestDto request);
  List<PricingDto> getPricing();
  PricingDto updatePricing(PricingDto request);
  boolean deletePricing(long id);
}
