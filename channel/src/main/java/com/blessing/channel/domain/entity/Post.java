package com.blessing.channel.domain.entity;
import com.blessing.channel.domain.dto.CommentDto;
import com.blessing.channel.domain.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
  private Integer likes = 0;


  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Comment> comments = new ArrayList<>();



  public Post(String title, String content, String author, Boolean isNotice) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.isNotice = isNotice;
    this.createdAt = LocalDateTime.now();
  }
  public static PostDto toDto(Post post) {
    PostDto dto = new PostDto();
    dto.setId(post.getId());
    dto.setTitle(post.getTitle());
    dto.setContent(post.getContent());
    dto.setAuthor(post.getAuthor());
    dto.setIsNotice(post.getIsNotice());
    dto.setCreatedAt(post.getCreatedAt());
    dto.setLikes(post.getLikes());

    List<CommentDto> commentDtos = post.getComments().stream().map(comment -> {
      CommentDto cdto = new CommentDto();
      cdto.setId(comment.getId());
      cdto.setAuthor(comment.getAuthor());
      cdto.setContent(comment.getContent());
      cdto.setLikes(comment.getLikes());
      cdto.setEmoji(comment.getEmoji() != null ? comment.getEmoji() : "");
      return cdto;
    }).collect(Collectors.toList());

    dto.setComments(commentDtos);
    return dto;
  }


}