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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trading_account_id", nullable = false)
    private TradingAccount tradingAccount;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column(nullable = false)
    private BigDecimal volume;

    @Column
    private BigDecimal openPrice;

    @Column
    private BigDecimal currentPrice;

    @Column
    private BigDecimal stopLoss;

    @Column
    private BigDecimal takeProfit;

    @Column
    private BigDecimal trailingStopDistance;

    @Column
    private BigDecimal commission = BigDecimal.ZERO;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @Column
    private BigDecimal executionPrice;

    @Column
    private LocalDateTime executedAt;

    @Column
    private LocalDateTime closedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum OrderType {
        MARKET, LIMIT, STOP, STOP_LIMIT, OCO
    }

    public enum Direction {
        BUY, SELL
    }

    public enum OrderStatus {
        PENDING, OPEN, PARTIALLY_FILLED, FILLED, CANCELLED, REJECTED, CLOSED
    }
}
