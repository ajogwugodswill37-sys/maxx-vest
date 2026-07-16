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
@Table(name = "market_symbols")
public class MarketSymbol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String symbol;

    @Column
    private String displayName;

    @Column(nullable = false)
    private String baseCurrency;

    @Column(nullable = false)
    private String quoteCurrency;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SymbolType symbolType;

    @Column
    private BigDecimal currentBid;

    @Column
    private BigDecimal currentAsk;

    @Column
    private BigDecimal pipValue;

    @Column
    private BigDecimal minLotSize;

    @Column
    private BigDecimal maxLotSize;

    @Column
    private BigDecimal spread;

    @Column
    private BigDecimal swapLong;

    @Column
    private BigDecimal swapShort;

    @Column(nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum SymbolType {
        FOREX, CRYPTO, COMMODITY, INDEX, STOCK
    }
}
