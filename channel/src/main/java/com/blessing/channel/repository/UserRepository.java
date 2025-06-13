package com.blessing.channel.repository;

import com.blessing.channel.domain.dto.RankedUserDto;
import com.blessing.channel.domain.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);

  @Query("SELECT COALESCE(SUM(u.totalDonation), 0) FROM User u")
  int getTotalDonationSum();

  @Query("SELECT new com.blessing.channel.domain.dto.RankedUserDto(u.name, u"
      + ".totalDonation) " +
      "FROM User u ORDER BY u.totalDonation DESC")
  List<RankedUserDto> findTop3ByPoint(Pageable pageable);

  boolean existsByName(String name);


}
