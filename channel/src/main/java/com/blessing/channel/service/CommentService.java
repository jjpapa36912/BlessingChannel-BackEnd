package com.blessing.channel.service;

import com.blessing.channel.domain.entity.Post;
import com.blessing.channel.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final PostRepository postRepository;

  public void deleteComment(Long postId, String author, String content) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

    List<String> updated;
    updated = post.getComments().stream()
        .filter(c -> !c.equals(author + ": " + content))
        .collect(Collectors.toList());

    post.setComments(updated);
    postRepository.save(post);
  }


}
