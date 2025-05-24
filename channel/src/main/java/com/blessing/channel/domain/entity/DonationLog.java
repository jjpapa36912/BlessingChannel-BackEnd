package com.blessing.channel.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  public DonationLog(User user, String section, String adType, int amount, LocalDateTime createdAt) {
    this.user = user;
    this.section = section;
    this.adType = adType;
    this.amount = amount;
    this.createdAt = createdAt;
  }

  @Id
  @GeneratedValue
  private Long id;

  private String section;
  private String adType;
  private int amount;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private LocalDateTime createdAt = LocalDateTime.now();
}

