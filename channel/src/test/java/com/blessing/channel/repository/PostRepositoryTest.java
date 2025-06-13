package com.blessing.channel.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.blessing.channel.domain.entity.Comment;
import com.blessing.channel.domain.entity.CommentReaction;
import com.blessing.channel.domain.entity.Post;
import com.blessing.channel.service.PostService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback(value = false)
class PostRepositoryTest {
  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private CommentReactionRepository commentReactionRepository;

  @BeforeEach
  void clearDatabase() {
    commentReactionRepository.deleteAllInBatch();
    commentRepository.deleteAllInBatch();
    postRepository.deleteAllInBatch();
  }


  @Test
  @DisplayName("게시글, 댓글, 리액션 더미 데이터 저장 테스트")
  void insertDummyData() {
    commentReactionRepository.deleteAllInBatch();
    commentRepository.deleteAllInBatch();
    postRepository.deleteAllInBatch();
    Random random = new Random();

    for (int i = 1; i <= 5; i++) {
      Post post = new Post(
          "테스트 제목 " + i,
          "테스트 내용입니다. 번호: " + i,
          "작성자" + i,
          i % 2 == 0 // isNotice
      );
      post.setLikes(random.nextInt(50));
      Post savedPost = postRepository.save(post);

      for (int j = 1; j <= 3; j++) {
        Comment comment = new Comment(
            "댓글작성자" + j,
            "이것은 댓글 " + j + "번입니다",
            savedPost
        );
        comment.setLikes(random.nextInt(10));
        comment.setEmoji("😊");
        Comment savedComment = commentRepository.save(comment);

        CommentReaction reaction = new CommentReaction();
        reaction.setPostId(savedPost.getId());
        reaction.setCommentId(savedComment.getId());
        reaction.setAuthor("반응작성자" + j);
        reaction.setEmoji("👍");
        commentReactionRepository.save(reaction);
      }
    }

    assertThat(postRepository.count()).isEqualTo(5);
    assertThat(commentRepository.count()).isEqualTo(15); // 5 posts × 3 comments
    assertThat(commentReactionRepository.count()).isEqualTo(15);

    System.out.println("✔ 더미 데이터 저장 테스트 성공");
  }
}
