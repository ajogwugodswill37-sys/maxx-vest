package com.maxvest.repository;

import com.maxvest.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByUserId(Long userId);
    List<Deposit> findByTradingAccountId(Long tradingAccountId);
    List<Deposit> findByStatus(Deposit.DepositStatus status);
}
