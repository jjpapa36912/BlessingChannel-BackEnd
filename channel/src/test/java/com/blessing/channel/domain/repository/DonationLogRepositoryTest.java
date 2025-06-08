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

class DonationLogRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @BeforeEach
    void setUp() {
      userRepository.deleteAll();

  }
  @Test
  @Transactional
  @Rollback(value = false)
  void save() {
    userRepository.deleteAll();
    // 1️⃣ 사용자 저장
    User user = new User();
    user.setName("김동준1");
    user.setTotalDonation(130);
    user.setTotalPoint(1);
    userRepository.save(user);

    User user1 = new User();
    user1.setName("김동준2");
    user1.setTotalDonation(130);
    user1.setTotalPoint(2);
    userRepository.save(user1);

    User user2 = new User();
    user2.setName("김동준3");
    user2.setTotalDonation(130);
    user2.setTotalPoint(188);
    userRepository.save(user2);

    User user3= new User();
    user3.setName("김동준4");
    user3.setTotalDonation(130);
    user3.setTotalPoint(200);
    userRepository.save(user3);

    // 2️⃣ 기부 로그 저장


    // 3️⃣ 검증
//    List<DonationLog> logs = donationLogRepository.findByName("김동준");
//    assertThat(logs).isNotEmpty();
//    assertThat(logs.get(0).getAmount()).isEqualTo(100);
  }



}