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
public class OrderDTO {
    private Long id;
    private String symbol;
    private String orderType;
    private String direction;
    private BigDecimal volume;
    private BigDecimal openPrice;
    private BigDecimal stopLoss;
    private BigDecimal takeProfit;
    private String status;
    private BigDecimal commission;
}
