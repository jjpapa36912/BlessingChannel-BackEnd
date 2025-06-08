package com.blessing.channel.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  private int totalDonation = 0;

  private int totalPoint = 0;

  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "last_banner_rewarded_at")
  private LocalDateTime lastBannerRewardedAt;
}
