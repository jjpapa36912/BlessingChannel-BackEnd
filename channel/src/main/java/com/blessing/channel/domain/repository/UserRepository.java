package com.blessing.channel.domain.repository;

import com.blessing.channel.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);
}
