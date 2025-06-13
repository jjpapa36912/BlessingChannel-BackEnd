package com.blessing.channel.controller;

import com.blessing.channel.domain.dto.CommentReactionRequest;
import com.blessing.channel.domain.dto.PagedPostResponse;
import com.blessing.channel.domain.dto.PostDto;
import com.blessing.channel.service.CommentReactionService;
import com.blessing.channel.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentReactionController {
  private final CommentReactionService reactionService;
  private final PostService postService;

  @PostMapping("/{postId}/comments/{commentId}/reaction")
  public ResponseEntity<Void> addReaction(
      @PathVariable Long postId,
      @PathVariable Long commentId,
      @RequestBody CommentReactionRequest request
  ) {
    reactionService.addReaction(postId, commentId, request);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{postId}/comments/{commentId}/reaction")
  public ResponseEntity<?> getReactions(
      @PathVariable Long postId,
      @PathVariable Long commentId
  ) {
    return ResponseEntity.ok(reactionService.getReactions(postId, commentId));
  }

//  // ✅ 게시글 페이징 처리 및 공지글 우선
//  @GetMapping("/paged")
//  public ResponseEntity<PagedPostResponse> getPagedPosts(
//      @RequestParam(defaultValue = "0") int page,
//      @RequestParam(defaultValue = "10") int size
//  ) {
//    Page<PostDto> pagedPosts = postService.getPagedPosts(PageRequest.of(page, size));
//    return ResponseEntity.ok(new PagedPostResponse(
//        pagedPosts.getContent(),
//        pagedPosts.getNumber(),
//        pagedPosts.getTotalPages(),
//        pagedPosts.getTotalElements()
//    ));
//  }
  @DeleteMapping("/{postId}/comments/{commentId}/with-auth")
  public ResponseEntity<Void> deleteCommentWithAuth(
      @PathVariable Long postId,
      @PathVariable Long commentId,
      @RequestParam String author
  ) {
    reactionService.deleteCommentIfOwner(postId, commentId, author);
    return ResponseEntity.ok().build();
  }


}
