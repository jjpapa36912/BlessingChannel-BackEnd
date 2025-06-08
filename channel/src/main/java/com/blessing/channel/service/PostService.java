package com.blessing.channel.service;

import com.blessing.channel.domain.dto.PostDto;
import com.blessing.channel.domain.dto.PostRequest;
import com.blessing.channel.domain.entity.Post;
import com.blessing.channel.repository.PostRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  // 글 생성
  public void createPost(String title, String content, String author, boolean isNotice) {
    Post post = new Post();
    post.setTitle(title);
    post.setContent(content);
    post.setAuthor(author);
    post.setCreatedAt(LocalDateTime.now());
    post.setIsNotice(isNotice);
    postRepository.save(post);
  }

  public void addComment(Long postId, String author, String content) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));

    String comment = author + ": " + content;
    post.getComments().add(comment); // ✅ 댓글 추가

    postRepository.save(post); // ✅ 댓글 리스트 변경 저장
  }

  // 글 수정
  public void updatePost(Long id, PostRequest request) {
    Optional<Post> optionalPost = postRepository.findById(id);
    boolean isNotice = "김동준".equals(request.getAuthor());
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      post.setTitle(request.getTitle());
      post.setContent(request.getContent());
      post.setIsNotice(isNotice);
      postRepository.save(post);
    } else {
      throw new IllegalArgumentException("게시글을 찾을 수 없습니다. ID=" + id);
    }
  }

  // 글 전체 조회
//  public List<Post> getAllPosts() {
//    return postRepository.findAll();
//  }
  public List<PostDto> getAllPosts() {
    return postRepository.findAllByOrderByIsNoticeDescCreatedAtDesc()
        .stream()
        .map(post -> new PostDto(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getAuthor(),
            post.getCreatedAt().toString(),
            post.getIsNotice(),
            post.getComments()
        ))
        .collect(Collectors.toList());
  }


  // 글 삭제
  public void deletePost(Long id) {
    postRepository.deleteById(id);
  }
}