package com.blessing.channel.domain.dto;

import lombok.Getter;
import lombok.Setter;

public class PostRequest {
  private String title;
  private String content;
  private String author;
  private boolean notice; // ✅ 공지 여부

  // Getter & Setter for `title`
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  // Getter & Setter for `content`
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  // Getter & Setter for `author`
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  // Getter & Setter for `notice` (boolean)
  public boolean isNotice() {
    return notice;
  }

  public void setNotice(boolean notice) {
    this.notice = notice;
  }
}

