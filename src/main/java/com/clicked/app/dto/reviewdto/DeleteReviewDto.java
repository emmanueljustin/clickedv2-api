package com.clicked.app.dto.reviewdto;

import java.util.List;

public class DeleteReviewDto {
  private List<Long> ids;

  public DeleteReviewDto() {}

  public List<Long> getIds() {
    return ids;
  }

  public void setIds(List<Long> ids) {
    this.ids = ids;
  }
}
