package com.blessing.channel.domain.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.blessing.channel.domain.entity.DonationLog;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DonationLogRepositoryTest {
  @Autowired
  DonationLogRepository donationLogRepository;

  @Test
  void save() {
    DonationLog donationLog = new DonationLog("1","1","!", 50,
        LocalDateTime.now());

    donationLogRepository.save(donationLog);
  }



}