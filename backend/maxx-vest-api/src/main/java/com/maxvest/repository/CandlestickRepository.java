package com.maxvest.repository;

import com.maxvest.entity.Candlestick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CandlestickRepository extends JpaRepository<Candlestick, Long> {
    List<Candlestick> findBySymbolAndTimeframeOrderByTimestampDesc(String symbol, String timeframe);
    List<Candlestick> findBySymbolAndTimeframeAndTimestampGreaterThanOrderByTimestampDesc(String symbol, String timeframe, LocalDateTime timestamp);
}
