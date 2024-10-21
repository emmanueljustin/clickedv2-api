package com.clicked.app.dto.reviewdto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageRequestDto {
  private int page;
  private int size;
}
