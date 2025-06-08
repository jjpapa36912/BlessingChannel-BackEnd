package com.blessing.channel.domain.dto;

import lombok.Data;

@Data
public class UserSummaryResponse {
  private int point;
  private int totalDonation;

  public UserSummaryResponse(int point, int totalDonation) {
    this.point = point;
    this.totalDonation = totalDonation;
  }

  // getters
}

