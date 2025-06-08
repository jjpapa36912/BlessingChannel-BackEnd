package com.blessing.channel.service;

import com.blessing.channel.domain.dto.DonationRequest;
import com.blessing.channel.domain.dto.RankedUserDto;
import com.blessing.channel.domain.dto.UserSummaryResponse;
import com.blessing.channel.domain.entity.User;
import com.blessing.channel.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

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

  public List<RankedUserDto> getTop3UsersByPoint() {
    return userRepository.findTop3ByPoint(PageRequest.of(0, 3));
  }

  public UserSummaryResponse registerOrUpdateUserWithBannerReward(String userId) {
    User user = userRepository.findByName(userId)
        .orElseGet(() -> {
          User newUser = new User();
          newUser.setName(userId);
          newUser.setCreatedAt(LocalDateTime.now());
          return newUser;
        });

    // 오늘 날짜 기준 적립 여부 확인
    LocalDate today = LocalDate.now();
    boolean rewardedToday = user.getLastBannerRewardedAt() != null &&
        user.getLastBannerRewardedAt().toLocalDate().isEqual(today);

    if (!rewardedToday) {
      user.setTotalDonation(user.getTotalDonation() + 4);      // 도네이션 +4
      user.setTotalPoint(user.getTotalPoint() + 0);            // 포인트는 0으로 시작, 변경 가능
      user.setLastBannerRewardedAt(LocalDateTime.now());       // 적립 일시 기록
    }

    userRepository.save(user);

    return new UserSummaryResponse(user.getTotalDonation(), user.getTotalPoint());
  }


}
