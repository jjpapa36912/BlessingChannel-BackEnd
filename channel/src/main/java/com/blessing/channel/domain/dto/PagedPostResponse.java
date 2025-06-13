package com.blessing.channel.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PagedPostResponse {
  private List<PostDto> posts;
  private int currentPage;
  private int totalPages;
  private long totalElements;
}