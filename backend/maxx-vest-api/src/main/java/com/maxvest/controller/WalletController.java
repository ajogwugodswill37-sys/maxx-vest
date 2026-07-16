package com.maxvest.controller;

import com.maxvest.dto.WalletDTO;
import com.maxvest.entity.Deposit;
import com.maxvest.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/wallet")
@RequiredArgsConstructor
@Tag(name = "Wallet", description = "Wallet and transaction endpoints")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/deposit")
    @Operation(summary = "Create deposit request")
    public ResponseEntity<String> deposit(
            @RequestParam Long userId,
            @RequestParam Long accountId,
            @RequestParam BigDecimal amount,
            @RequestParam Deposit.PaymentMethod paymentMethod) {
        log.info("Deposit request for user: {} amount: {}", userId, amount);
        Deposit deposit = walletService.deposit(userId, accountId, amount, paymentMethod);
        return ResponseEntity.status(HttpStatus.CREATED).body("Deposit created with ID: " + deposit.getId());
    }

    @GetMapping("/deposits")
    @Operation(summary = "Get user deposits")
    public ResponseEntity<List<Object>> getDeposits(@RequestParam Long userId) {
        return ResponseEntity.ok(walletService.getUserDeposits(userId).stream()
                .map(d -> new Object() {
                    public final Long id = d.getId();
                    public final BigDecimal amount = d.getAmount();
                    public final String status = d.getStatus().toString();
                }).collect(Collectors.toList()));
    }

    @PostMapping("/deposits/{depositId}/approve")
    @Operation(summary = "Approve deposit")
    public ResponseEntity<Void> approveDeposit(@PathVariable Long depositId) {
        walletService.approveDeposit(depositId);
        return ResponseEntity.noContent().build();
    }
}
