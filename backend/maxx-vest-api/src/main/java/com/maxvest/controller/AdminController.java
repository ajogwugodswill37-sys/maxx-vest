package com.maxvest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin dashboard endpoints")
public class AdminController {

    @GetMapping("/stats")
    @Operation(summary = "Get system statistics")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", 0);
        stats.put("activeTraders", 0);
        stats.put("totalVolume", "0.00");
        stats.put("totalCommissions", "0.00");
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/health")
    @Operation(summary = "Get system health status")
    public ResponseEntity<Map<String, Object>> getHealth() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("database", "CONNECTED");
        health.put("cache", "CONNECTED");
        return ResponseEntity.ok(health);
    }
}
