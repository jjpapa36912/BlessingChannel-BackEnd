package com.blessing.channel.controller;

import com.blessing.channel.domain.dto.DonationRequest;
import com.blessing.channel.domain.dto.UserSummaryDto;
import com.blessing.channel.domain.entity.User;
import com.blessing.channel.repository.UserRepository;
import com.blessing.channel.service.UserService;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserRepository userRepository;
  private final UserService userService;

  public UserController(UserRepository userRepository,
      UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  @GetMapping("/{userId}/summary")
  public ResponseEntity<?> getSummary(@PathVariable String userId) {
    User user = userRepository.findByName(userId).orElseThrow();

    int point = user.getTotalPoint();
    int totalDonation = user.getTotalDonation();

    return ResponseEntity.ok(Map.of(
        "point", point,
        "totalDonation", totalDonation
    ));
  }
  @GetMapping("/name/{name}/summary")
  public ResponseEntity<Map<String, Integer>> getUserSummaryByName(@PathVariable String name) {
    User user = userRepository.findByName(name)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    Map<String, Integer> result = Map.of(
        "totalDonation", user.getTotalDonation(),
        "totalPoint", user.getTotalPoint()
    );
    return ResponseEntity.ok(result);
  }



  // ✅ POST /api/users/{name}/summary
  @PostMapping("/{name}/summary")
  public ResponseEntity<Void> updateSummary(
      @PathVariable String name,
      @RequestBody UserSummaryDto dto) {

    int point = dto.getPoint();

    User user = userRepository.findByName(name)
        .orElseGet(() -> {
          User newUser = new User();
          newUser.setName(name);
          return newUser;
        });

    user.setTotalPoint(point);
    userRepository.save(user);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/{userId}/summary")
  public ResponseEntity<?> registerUserIfNotExists(
      @PathVariable String userId,
      @RequestBody UserSummaryDto dto) {

    // 이미 존재하면 아무것도 안 함
    Optional<User> optional = userRepository.findByName(userId);
    if (optional.isPresent()) {
      return ResponseEntity.ok(Map.of("message", "User already exists"));
    }

    // 신규 유저 생성
    User newUser = new User();
    newUser.setName(userId);
    newUser.setTotalPoint(dto.getPoint()); // 기본 0

    userRepository.save(newUser);

    return ResponseEntity.ok(Map.of("message", "User created"));
  }

//  @GetMapping("/points/total")
//  public Map<String, Integer> getTotalPoints() {
//    Integer totalDonation = logRepo.getTotalDonation();
//    int totalPoints = (totalDonation == null ? 0 : totalDonation / 10);
//    return Map.of("totalPoints", totalPoints);
//  }

//  @GetMapping("/api/users/{name}/summary")
//  public ResponseEntity<Map<String, Object>> getUserSummary(@PathVariable String name) {
//    User user = userRepository.findByName(name).orElseThrow();
//    Map<String, Object> response = new HashMap<>();
//    response.put("totalDonation", user.getTotalDonation());
//    response.put("point", user.getTotalPoint());
//    return ResponseEntity.ok(response);
//  }

  // UserController.java
  @PostMapping("/{userId}/donate")
  public ResponseEntity<?> donate(
      @PathVariable Long userId,
      @RequestParam int amount,
      @RequestParam String section,
      @RequestParam String adType
  ) {
    User user = userRepository.findById(userId).orElseThrow();
    userService.addDonation(new DonationRequest(amount, section, adType));
    return ResponseEntity.ok().build();
  }
  @GetMapping("/total-donation")
  public Map<String, Integer> getTotalDonationFromUsers() {
    int total = userRepository.sumAllUserDonations(); // 사용자 정의 쿼리 필요
    return Map.of("totalDonation", total);
  }
  @GetMapping("/{name}/summary")
  public Map<String, Integer> getUserSummary(@PathVariable String name) {
    User user = userRepository.findByName(name)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return Map.of(
        "totalDonation", user.getTotalDonation(),
        "totalPoint", user.getTotalPoint()
    );
  }

  @PostMapping("/{name}/reward")
  public ResponseEntity<?> rewardUser(@PathVariable String name, @RequestParam int amount) {
    User user = userRepository.findByName(name)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    user.setTotalDonation(user.getTotalDonation() + amount);
    user.setTotalPoint(user.getTotalDonation() / 10);
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }



}
