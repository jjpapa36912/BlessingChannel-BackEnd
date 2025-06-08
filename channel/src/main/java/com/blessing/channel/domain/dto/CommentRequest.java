package com.blessing.channel.domain.dto;

import lombok.Data;

@Data
public class CommentRequest {
  private String author;
  private String content;
  // getter/setter
}