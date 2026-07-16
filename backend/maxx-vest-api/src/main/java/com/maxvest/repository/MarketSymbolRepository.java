package com.maxvest.repository;

import com.maxvest.entity.MarketSymbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketSymbolRepository extends JpaRepository<MarketSymbol, Long> {
    Optional<MarketSymbol> findBySymbol(String symbol);
    List<MarketSymbol> findByIsActive(Boolean isActive);
}
