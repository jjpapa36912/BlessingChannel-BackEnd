package com.blessing.channel.domain.dto;

public class RankedUserDto {
  private String name;
  private int point;

  public RankedUserDto(String name, int point) {
    this.name = name;
    this.point = point;
  }

  // getter
  public String getName() { return name; }
  public int getPoint() { return point; }
}

