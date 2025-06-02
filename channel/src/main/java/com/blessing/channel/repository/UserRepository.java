package com.blessing.channel.repository;

import com.blessing.channel.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);
  @Query("SELECT SUM(u.totalDonation) FROM User u")
  int sumAllUserDonations();


}
