package com.maxvest.service;

import com.maxvest.entity.MarketSymbol;

import java.util.List;
import java.util.Optional;

public interface MarketDataService {
    List<MarketSymbol> getAllSymbols();
    Optional<MarketSymbol> getSymbol(String symbol);
    void updateSymbolPrice(String symbol, java.math.BigDecimal bid, java.math.BigDecimal ask);
}
