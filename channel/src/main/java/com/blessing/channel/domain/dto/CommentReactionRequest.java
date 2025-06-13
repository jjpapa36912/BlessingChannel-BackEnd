package com.blessing.channel.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReactionRequest {
  private String author;
  private String emoji;
}

