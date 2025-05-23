package com.blessing.channel.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "donation_logs")
public class DonationLog {

  public DonationLog(String userId, String section, String adType, int amount,
      LocalDateTime createdAt) {
    this.userId = userId;
    this.section = section;
    this.adType = adType;
    this.amount = amount;
    this.createdAt = createdAt;
  }

  @Id
  @GeneratedValue
  private Long id;

  private String userId;
  private String section;
  private String adType;
  private int amount;
  private LocalDateTime createdAt = LocalDateTime.now();
}

