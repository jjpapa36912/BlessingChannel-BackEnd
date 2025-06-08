package com.blessing.channel.domain.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  private String author;

  private Boolean isNotice = false;

  private LocalDateTime createdAt = LocalDateTime.now();


  @ElementCollection
  private List<String> comments = new ArrayList<>();

  public Post(String title, String content, String author, Boolean isNotice) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.isNotice = isNotice;
    this.createdAt = LocalDateTime.now();
  }
}