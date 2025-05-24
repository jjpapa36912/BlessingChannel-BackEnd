package com.blessing.channel.controller;

import com.blessing.channel.domain.entity.User;
import com.blessing.channel.domain.repository.DonationLogRepository;
import com.blessing.channel.domain.repository.UserRepository;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserRepository userRepo;
  private final DonationLogRepository logRepo;

  public UserController(UserRepository userRepo, DonationLogRepository logRepo) {
    this.userRepo = userRepo;
    this.logRepo = logRepo;
  }

  @GetMapping("/{userId}/summary")
  public ResponseEntity<?> getSummary(@PathVariable String userId) {
    User user = userRepo.findByName(userId).orElseThrow();

    int totalDonation = logRepo.sumDonationByUser(user);
    int point = user.getPoint();

    return ResponseEntity.ok(Map.of(
        "point", point,
        "totalDonation", totalDonation
    ));
  }
}
