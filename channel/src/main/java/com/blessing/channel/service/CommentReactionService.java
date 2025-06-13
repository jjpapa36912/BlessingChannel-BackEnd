package com.blessing.channel.service;

import com.blessing.channel.domain.entity.CommentReaction;
import com.blessing.channel.domain.dto.CommentReactionRequest;
import com.blessing.channel.repository.CommentReactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentReactionService {

  private final CommentReactionRepository reactionRepository;

  public void addReaction(Long postId, Long commentId, CommentReactionRequest request) {
    CommentReaction reaction = new CommentReaction();
    reaction.setPostId(postId);
    reaction.setCommentId(commentId);
    reaction.setAuthor(request.getAuthor());
    reaction.setEmoji(request.getEmoji());
    reactionRepository.save(reaction);
  }
  public void deleteCommentIfOwner(Long postId, Long commentId, String author) {
    List<CommentReaction> all = reactionRepository.findByPostIdAndCommentId(postId, commentId);
    boolean isOwner = all.stream().anyMatch(r -> r.getAuthor().equals(author));
    if (isOwner) {
      reactionRepository.deleteAll(all);
    } else {
      throw new RuntimeException("삭제 권한이 없습니다.");
    }
  }

  public List<CommentReaction> getReactions(Long postId, Long commentId) {
    return reactionRepository.findByPostIdAndCommentId(postId, commentId);
  }
}
