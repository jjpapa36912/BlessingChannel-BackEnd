package com.blessing.channel.repository;

import com.blessing.channel.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByOrderByIsNoticeDescCreatedAtDesc();
  Page<Post> findAllByOrderByIsNoticeDescCreatedAtDesc(Pageable pageable);

}
