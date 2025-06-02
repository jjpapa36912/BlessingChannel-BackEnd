package com.blessing.channel.service;

import com.blessing.channel.domain.dto.DonationRequest;
import com.blessing.channel.domain.entity.User;
import com.blessing.channel.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private UserRepository userRepository;

  @Transactional
  public void addDonation(DonationRequest dto) {
    // 1. 사용자 조회
    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));


    // 3. 유저 누적 기부금 및 포인트 갱신
    int newTotalDonation = user.getTotalDonation() + dto.getAmount();
    user.setTotalDonation(newTotalDonation);
    user.setTotalPoint(newTotalDonation / 10);

    userRepository.save(user);
  }


}
