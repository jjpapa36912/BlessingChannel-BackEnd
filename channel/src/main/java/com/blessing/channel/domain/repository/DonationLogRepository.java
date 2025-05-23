package com.blessing.channel.domain.repository;

import com.blessing.channel.domain.entity.DonationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonationLogRepository extends
    JpaRepository<DonationLog, Long> {
  @Query("SELECT SUM(d.amount) FROM DonationLog d")
  Integer getTotalDonation();
}
