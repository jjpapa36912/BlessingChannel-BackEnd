package com.blessing.channel.domain.dto;

import com.blessing.channel.domain.entity.Comment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
  private Long id;
  private String title;
  private String content;
  private String author;
  private LocalDateTime createdAt;
  private Boolean isNotice;
  private List<CommentDto> comments = new ArrayList<>();

  private Integer likes;


  public PostDto(PostDto postDto) {

  }
}