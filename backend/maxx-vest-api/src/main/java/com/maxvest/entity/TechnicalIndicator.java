package com.maxvest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "technical_indicators")
public class TechnicalIndicator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IndicatorType indicatorType;

    @Column(nullable = false)
    private String timeframe;

    @Column(nullable = false)
    private BigDecimal value;

    @Column
    private Integer period;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum IndicatorType {
        EMA, SMA, RSI, MACD, BB_UPPER, BB_LOWER, BB_MIDDLE, STOCHASTIC, ATR
    }
}
