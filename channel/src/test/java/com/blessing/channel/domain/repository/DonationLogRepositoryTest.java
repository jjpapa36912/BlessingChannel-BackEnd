package com.blessing.channel.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.blessing.channel.domain.entity.User;
import com.blessing.channel.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(value = false)
class DonationLogRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }
  @Test
  void save() {
    // 1️⃣ 사용자 저장
    User user = new User();
    user.setName("김동준");
    user.setTotalDonation(130);
    user.setTotalPoint(13);
    userRepository.save(user);

    // 2️⃣ 기부 로그 저장


    // 3️⃣ 검증
//    List<DonationLog> logs = donationLogRepository.findByName("김동준");
//    assertThat(logs).isNotEmpty();
//    assertThat(logs.get(0).getAmount()).isEqualTo(100);
  }



}