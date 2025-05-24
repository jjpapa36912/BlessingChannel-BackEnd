package com.blessing.channel.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String email;
  private int point; // 누적 포인트

  private LocalDateTime createdAt = LocalDateTime.now();

  // ✅ getter/setter 필수
}
