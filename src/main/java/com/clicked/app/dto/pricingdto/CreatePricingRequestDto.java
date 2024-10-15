package com.clicked.app.dto.pricingdto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePricingRequestDto {
  private String title;
  private String description;
  private List<CreateServiceRequestDto> services;
  private BigDecimal amount;
  private String btnName;
  private String color;
}
