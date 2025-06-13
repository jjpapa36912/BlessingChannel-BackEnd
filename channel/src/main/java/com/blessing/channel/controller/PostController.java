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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "isNotice", "createdAt"));
    return posts.stream().map(Post::toDto).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("게시글 없음"));
    PostDto dto = post.toDto(post); // 또는 내부에서 toDto(post) 호출
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/paged")
  public ResponseEntity<List<PostDto>> getPagedPosts(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "isNotice", "createdAt"));
    Page<Post> postPage = postRepository.findAll(pageable);

    List<PostDto> dtos = postPage.getContent().stream()
        .map(Post::toDto)
        .collect(Collectors.toList());

    return ResponseEntity.ok(dtos);
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
//  @PostMapping("/board/{postId}/comments")
//  public ResponseEntity<?> addComment(@PathVariable Long postId, @RequestBody CommentRequest request) {
//    postService.addComment(postId, request.getAuthor(), request.getContent());
//    return ResponseEntity.ok().build();
//  }
//  @DeleteMapping("/board/{postId}/comments")
//  public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @RequestBody CommentRequest request) {
//    commentService.deleteComment(postId, request.getAuthor(), request.getContent());
//    return ResponseEntity.ok().build();
//  }

}