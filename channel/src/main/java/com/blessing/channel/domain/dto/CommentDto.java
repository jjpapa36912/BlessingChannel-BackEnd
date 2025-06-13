package com.blessing.channel.domain.dto;

import com.blessing.channel.domain.entity.Comment;
import lombok.Data;

@Data
public class CommentDto {
  private Long id;
  private String author;
  private String content;
  private int likes = 0;
  private String emoji;
}
