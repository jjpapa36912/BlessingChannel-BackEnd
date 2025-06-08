package com.blessing.channel.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.blessing.channel.domain.entity.Post;
import com.blessing.channel.service.PostService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class PostRepositoryTest {
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private PostService postService;

  @BeforeEach
  void setUp() {
    postRepository.deleteAll(); // 기존 데이터 삭제
  }

  @Test
  @Transactional
  @Rollback(value = false)
  void saveSamplePosts() {
    // ✅ 샘플 게시글 3개 저장
    postRepository.save(new Post("첫 글", "이것은 첫 번째 게시글입니다.", "김동준", true));
    postRepository.save(new Post("두 번째 글", "이것은 두 번째 게시글입니다.", "홍길동", false));
    postRepository.save(new Post("세 번째 글", "이것은 세 번째 게시글입니다.", "이영희", false));

    // 🔹 1. 게시글 먼저 저장
    Post post = new Post();
    post.setTitle("테스트 제목");
    post.setContent("테스트 내용");
    post.setAuthor("김동준test");
    post.setCreatedAt(LocalDateTime.now());

    post = postRepository.save(post);
    // 🔹 2. 댓글 추가
    postService.addComment(post.getId(), "김동준", "첫 댓글입니다");

    // ✅ 저장된 데이터 검증
    List<Post> posts = postRepository.findAll();
//    assertThat(posts).hasSize(3);

    posts.forEach(p ->
        System.out.println(p.getTitle() + " / " + p.getAuthor() + " / " + p.getCreatedAt())
    );
  }

}