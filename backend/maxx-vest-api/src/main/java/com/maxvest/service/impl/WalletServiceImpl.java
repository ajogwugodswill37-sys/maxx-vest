package com.maxvest.service.impl;

import com.maxvest.entity.*;
import com.maxvest.exception.ResourceNotFoundException;
import com.maxvest.repository.*;
import com.maxvest.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final DepositRepository depositRepository;
    private final WithdrawalRepository withdrawalRepository;
    private final TransactionRepository transactionRepository;
    private final TradingAccountRepository tradingAccountRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Deposit deposit(Long userId, Long accountId, BigDecimal amount, Deposit.PaymentMethod paymentMethod) {
        log.info("Processing deposit for user: {} amount: {}", userId, amount);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        TradingAccount account = tradingAccountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Trading account not found"));

        Deposit deposit = Deposit.builder()
                .user(user)
                .tradingAccount(account)
                .amount(amount)
                .currency(account.getBaseCurrency())
                .paymentMethod(paymentMethod)
                .status(Deposit.DepositStatus.PENDING)
                .build();

        return depositRepository.save(deposit);
    }

    @Override
    @Transactional
    public void approveDeposit(Long depositId) {
        Deposit deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new ResourceNotFoundException("Deposit not found"));
        
        deposit.setStatus(Deposit.DepositStatus.COMPLETED);
        deposit.setCompletedAt(LocalDateTime.now());
        depositRepository.save(deposit);

        TradingAccount account = deposit.getTradingAccount();
        account.setBalance(account.getBalance().add(deposit.getAmount()));
        account.setEquity(account.getEquity().add(deposit.getAmount()));
        tradingAccountRepository.save(account);

        log.info("Deposit approved: {} for user: {}", depositId, deposit.getUser().getId());
    }

    @Override
    @Transactional
    public void rejectDeposit(Long depositId, String reason) {
        Deposit deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new ResourceNotFoundException("Deposit not found"));
        
        deposit.setStatus(Deposit.DepositStatus.FAILED);
        deposit.setRejectionReason(reason);
        depositRepository.save(deposit);
        log.info("Deposit rejected: {} reason: {}", depositId, reason);
    }

    @Override
    public List<Deposit> getUserDeposits(Long userId) {
        return depositRepository.findByUserId(userId);
    }
}
