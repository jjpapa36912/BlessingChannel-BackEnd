package com.blessing.channel.controller;

import com.blessing.channel.domain.dto.AdReportDto;
import com.blessing.channel.domain.entity.DonationLog;
import com.blessing.channel.domain.repository.DonationLogRepository;
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
  private final DonationLogRepository repo;

  public AdReportController(DonationLogRepository repo) {
    this.repo = repo;



  }

  @PostMapping("/report")
  public ResponseEntity<?> reportAd(@RequestBody AdReportDto dto) {
    DonationLog log = new DonationLog();
    log.setUserId(dto.getUserId());
    log.setSection(dto.getSection());
    log.setAdType(dto.getAdType());
    log.setAmount(dto.getAmount());
    repo.save(log);
    return ResponseEntity.ok(Map.of("success", true));
  }

  @GetMapping("/total")
  public Map<String, Integer> getTotalDonation() {
    Integer total = repo.getTotalDonation();
    return Map.of("totalDonation", total == null ? 0 : total);
  }
}

