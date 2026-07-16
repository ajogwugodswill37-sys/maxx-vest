package com.maxvest.repository;

import com.maxvest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByTradingAccountId(Long tradingAccountId);
    List<Order> findBySymbol(String symbol);
}
