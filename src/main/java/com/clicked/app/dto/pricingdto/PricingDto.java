package com.clicked.app.dto.pricingdto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PricingDto {
  private long id;
  private String title;
  private String description;
  private List<ServiceDto> services;
  private BigDecimal amount;
  private String btnName;
  private String color;
}
