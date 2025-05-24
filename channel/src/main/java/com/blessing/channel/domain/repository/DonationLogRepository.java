package com.blessing.channel.domain.repository;

import com.blessing.channel.domain.entity.DonationLog;
import com.blessing.channel.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DonationLogRepository extends
    JpaRepository<DonationLog, Long> {
  @Query("SELECT SUM(d.amount) FROM DonationLog d")
  Integer getTotalDonation();

  @Query("SELECT SUM(d.amount) FROM DonationLog d WHERE d.user = :user")
  Integer sumDonationByUser(@Param("user") User user);
}
