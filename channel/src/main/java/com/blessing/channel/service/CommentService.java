package com.blessing.channel.service;

import com.blessing.channel.domain.entity.Comment;
import com.blessing.channel.domain.entity.Post;
import com.blessing.channel.repository.CommentRepository;
import com.blessing.channel.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  public void addComment(Long postId, String author, String content) {
    Optional<Post> optionalPost = postRepository.findById(postId);
    if (optionalPost.isEmpty()) {
      throw new IllegalArgumentException("Post not found with id: " + postId);
    }
    Post post = optionalPost.get();

    List<Comment> comments = post.getComments();
    int commentId = comments.size();

    Comment newComment = new Comment();
    newComment.setAuthor(author);
    newComment.setContent(content);
    newComment.setLikes(0);
    newComment.setEmoji("");
    newComment.setPost(post); // 반드시 post 설정해야 양방향 관계 연결됨

    comments.add(newComment);
    postRepository.save(post);
  }

  public void deleteComment(Long postId, Long commentId, String author) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + commentId));

    if (!comment.getPost().getId().equals(postId)) {
      throw new IllegalArgumentException("Post ID does not match the comment");
    }

    if (!comment.getAuthor().equals(author)) {
      throw new SecurityException("작성자만 삭제할 수 있습니다");
    }

    commentRepository.delete(comment);
  }


  public List<Comment> getAllComments(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
    return post.getComments();
  }

}
