package com.blessing.channel.domain.dto;

import com.blessing.channel.domain.entity.Post;
import java.time.format.DateTimeFormatter;
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
  private String createdAt;
  private Boolean isNotice;
  private List<String> comments;


  public PostDto(PostDto postDto) {

  }
}