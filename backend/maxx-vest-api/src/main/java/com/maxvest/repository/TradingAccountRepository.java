package com.maxvest.repository;

import com.maxvest.entity.TradingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradingAccountRepository extends JpaRepository<TradingAccount, Long> {
    List<TradingAccount> findByUserId(Long userId);
    Optional<TradingAccount> findByAccountNumber(String accountNumber);
}
