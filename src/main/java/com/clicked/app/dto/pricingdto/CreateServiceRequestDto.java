package com.clicked.app.dto.pricingdto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateServiceRequestDto {
  private String serviceName;
  private boolean included;
}
