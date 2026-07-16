package com.maxvest.repository;

import com.maxvest.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByTradingAccountIdAndStatus(Long tradingAccountId, Position.PositionStatus status);
    List<Position> findBySymbol(String symbol);
}
