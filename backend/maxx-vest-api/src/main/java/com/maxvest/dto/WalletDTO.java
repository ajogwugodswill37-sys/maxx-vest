package com.maxvest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletDTO {
    private Long id;
    private String baseCurrency;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private BigDecimal reservedBalance;
    private BigDecimal totalDeposits;
    private BigDecimal totalWithdrawals;
    private LocalDateTime updatedAt;
}
