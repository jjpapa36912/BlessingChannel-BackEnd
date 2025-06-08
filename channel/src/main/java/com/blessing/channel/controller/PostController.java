package com.blessing.channel.controller;

import com.blessing.channel.domain.dto.CommentRequest;
import com.blessing.channel.domain.dto.PostDto;
import com.blessing.channel.domain.dto.PostRequest;
import com.blessing.channel.domain.entity.Post;
import com.blessing.channel.repository.PostRepository;
import com.blessing.channel.service.CommentService;
import com.blessing.channel.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostRepository postRepository;
  private final PostService postService;
  private final CommentService commentService;


  @GetMapping
  public List<PostDto> getAllPosts() {
    return postService.getAllPosts(); // 이미 DTO임 → 다시 매핑할 필요 없음 ✅
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    postRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
  @PostMapping
  public ResponseEntity<Void> createPost(@RequestBody PostRequest request) {
    boolean isNotice = "김동준".equals(request.getAuthor());
    postService.createPost(request.getTitle(), request.getContent(), request.getAuthor(), isNotice);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
    postService.updatePost(id, request);
    return ResponseEntity.ok().build();
  }
  @PostMapping("/board/{postId}/comments")
  public ResponseEntity<?> addComment(@PathVariable Long postId, @RequestBody CommentRequest request) {
    postService.addComment(postId, request.getAuthor(), request.getContent());
    return ResponseEntity.ok().build();
  }
  @DeleteMapping("/board/{postId}/comments")
  public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @RequestBody CommentRequest request) {
    commentService.deleteComment(postId, request.getAuthor(), request.getContent());
    return ResponseEntity.ok().build();
  }

}