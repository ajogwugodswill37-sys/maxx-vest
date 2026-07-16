package com.maxvest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trading_accounts")
public class TradingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String accountName;

    @Column(nullable = false)
    private String baseCurrency = "USD";

    @Column(nullable = false)
    private BigDecimal leverage = new BigDecimal("50.00");

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal equity = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal freeMargin = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal usedMargin = BigDecimal.ZERO;

    @Column
    private BigDecimal marginLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.STANDARD;

    @Column(nullable = false)
    private Boolean isDefault = false;

    @Column(nullable = false)
    private BigDecimal totalCommissions = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal totalSwaps = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal totalProfitLoss = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum AccountStatus {
        ACTIVE, SUSPENDED, CLOSED, RESTRICTED
    }

    public enum AccountType {
        MICRO, MINI, STANDARD, PREMIUM, PROFESSIONAL
    }
}
