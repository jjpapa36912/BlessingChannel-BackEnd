package com.blessing.channel.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest {
  private Long userId;
  private int amount;
  private String section;
  private String adType;

  public DonationRequest(int amount, String section, String adType) {
    this.amount = amount;
    this.section = section;
    this.adType = adType;
  }

  // getter/setter
}

