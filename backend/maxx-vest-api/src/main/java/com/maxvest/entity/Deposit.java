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
@Table(name = "deposits")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "trading_account_id", nullable = false)
    private TradingAccount tradingAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(unique = true)
    private String paymentReference;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DepositStatus status = DepositStatus.PENDING;

    @Column
    private String rejectionReason;

    @Column
    private LocalDateTime processedAt;

    @Column
    private LocalDateTime completedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum PaymentMethod {
        BANK_TRANSFER, CREDIT_CARD, DEBIT_CARD, CRYPTO, E_WALLET
    }

    public enum DepositStatus {
        PENDING, PROCESSING, COMPLETED, FAILED, CANCELLED
    }
}
