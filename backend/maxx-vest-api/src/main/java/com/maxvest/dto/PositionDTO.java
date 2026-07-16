package com.maxvest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionDTO {
    private Long id;
    private String symbol;
    private String direction;
    private BigDecimal volume;
    private BigDecimal openPrice;
    private BigDecimal currentPrice;
    private BigDecimal currentProfitLoss;
    private BigDecimal profitLossPercent;
    private String status;
}
