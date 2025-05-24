package com.blessing.channel.controller;

import com.blessing.channel.domain.dto.AdReportDto;
import com.blessing.channel.domain.entity.DonationLog;
import com.blessing.channel.domain.entity.User;
import com.blessing.channel.domain.repository.DonationLogRepository;
import com.blessing.channel.domain.repository.UserRepository;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ads")
public class AdReportController {
  private final DonationLogRepository logRepo;
  private final UserRepository userRepo;

  public AdReportController(DonationLogRepository logRepo, UserRepository userRepo) {
    this.logRepo = logRepo;
    this.userRepo = userRepo;
  }

  @PostMapping("/report")
  public ResponseEntity<?> report(@RequestBody AdReportDto dto) {
    User user = userRepo.findByName(dto.getUserId())
        .orElseGet(() -> {
          User newUser = new User();
          newUser.setName(dto.getUserId());
          newUser.setEmail(""); // 필요 시
          return userRepo.save(newUser);
        });

    DonationLog log = new DonationLog();
    log.setUser(user);
    log.setAdType(dto.getAdType());
    log.setSection(dto.getSection());
    log.setAmount(dto.getAmount());
    logRepo.save(log);

    // ✅ 포인트 누적 (기부금의 10%)
    int earnedPoint = dto.getAmount() / 10;
    user.setPoint(user.getPoint() + earnedPoint);
    userRepo.save(user);

    return ResponseEntity.ok(Map.of("success", true));
  }

  @GetMapping("/total")
  public Map<String, Integer> getTotalDonation() {
    Integer total = logRepo.getTotalDonation();
    return Map.of("totalDonation", total == null ? 0 : total);
  }
}

