package com.blessing.channel.controller;

import com.blessing.channel.domain.dto.CommentRequest;
import com.blessing.channel.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/{postId}/comments")
  public ResponseEntity<Void> addComment(
      @PathVariable Long postId,
      @RequestBody CommentRequest request
  ) {
    commentService.addComment(postId, request.getAuthor(), request.getContent());
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{postId}/comments/{commentId}")
  public ResponseEntity<Void> deleteComment(
      @PathVariable Long postId,
      @PathVariable Long commentId,
      @RequestParam String author
  ) {
    commentService.deleteComment(postId, commentId, author);
    return ResponseEntity.ok().build();
  }
}