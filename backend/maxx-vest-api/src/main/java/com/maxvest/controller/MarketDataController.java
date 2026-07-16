package com.maxvest.controller;

import com.maxvest.entity.MarketSymbol;
import com.maxvest.service.MarketDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/symbols")
@RequiredArgsConstructor
@Tag(name = "Market Data", description = "Market data and symbols endpoints")
public class MarketDataController {

    private final MarketDataService marketDataService;

    @GetMapping
    @Operation(summary = "Get all symbols")
    public ResponseEntity<List<MarketSymbol>> getAllSymbols() {
        return ResponseEntity.ok(marketDataService.getAllSymbols());
    }

    @GetMapping("/{symbol}")
    @Operation(summary = "Get symbol details")
    public ResponseEntity<MarketSymbol> getSymbol(@PathVariable String symbol) {
        return marketDataService.getSymbol(symbol)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
