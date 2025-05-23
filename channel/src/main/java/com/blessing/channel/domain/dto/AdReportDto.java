package com.blessing.channel.domain.dto;

import lombok.Data;

@Data
public class AdReportDto {
  private String userId;
  private String section;
  private String adType;
  private int amount;
}
