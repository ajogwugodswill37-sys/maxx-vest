package com.maxvest.controller;

import com.maxvest.dto.PositionDTO;
import com.maxvest.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
@Tag(name = "Positions", description = "Position management endpoints")
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    @Operation(summary = "Get open positions")
    public ResponseEntity<List<PositionDTO>> getPositions(@RequestParam Long accountId) {
        return ResponseEntity.ok(positionService.getOpenPositions(accountId));
    }

    @GetMapping("/{positionId}")
    @Operation(summary = "Get position details")
    public ResponseEntity<PositionDTO> getPosition(@PathVariable Long positionId) {
        return ResponseEntity.ok(positionService.getPosition(positionId));
    }

    @PostMapping("/{positionId}/close")
    @Operation(summary = "Close position")
    public ResponseEntity<PositionDTO> closePosition(@PathVariable Long positionId) {
        return ResponseEntity.ok(positionService.closePosition(positionId));
    }
}
