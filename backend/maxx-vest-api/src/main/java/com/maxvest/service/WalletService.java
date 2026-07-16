package com.maxvest.service;

import com.maxvest.entity.Deposit;
import java.util.List;

public interface WalletService {
    Deposit deposit(Long userId, Long accountId, java.math.BigDecimal amount, Deposit.PaymentMethod paymentMethod);
    void approveDeposit(Long depositId);
    void rejectDeposit(Long depositId, String reason);
    List<Deposit> getUserDeposits(Long userId);
}
