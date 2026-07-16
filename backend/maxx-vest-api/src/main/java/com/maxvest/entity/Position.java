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
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trading_account_id", nullable = false)
    private TradingAccount tradingAccount;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Order.Direction direction;

    @Column(nullable = false)
    private BigDecimal volume;

    @Column(nullable = false)
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
    private BigDecimal currentProfitLoss = BigDecimal.ZERO;

    @Column
    private BigDecimal profitLossPercent;

    @Column
    private BigDecimal pips;

    @Column
    private BigDecimal marginUsed;

    @Column(nullable = false)
    private BigDecimal swap = BigDecimal.ZERO;

    @Column
    private LocalDateTime swapTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PositionStatus status = PositionStatus.OPEN;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime openedAt;

    @Column
    private LocalDateTime closedAt;

    @Column
    private BigDecimal closingPrice;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum PositionStatus {
        OPEN, CLOSED, PARTIALLY_CLOSED
    }
}
