package com.blessing.channel.repository;

import com.blessing.channel.domain.entity.CommentReaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactionRepository extends
    JpaRepository<CommentReaction, Long> {
  Optional<CommentReaction> findByCommentIdAndAuthor(Long commentId, String author);
  List<CommentReaction> findByCommentId(Long commentId);
  List<CommentReaction> findByPostIdAndCommentId(Long postId, Long commentId);

}

