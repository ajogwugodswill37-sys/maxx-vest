package com.maxvest.service.impl;

import com.maxvest.dto.PositionDTO;
import com.maxvest.entity.Position;
import com.maxvest.exception.ResourceNotFoundException;
import com.maxvest.repository.PositionRepository;
import com.maxvest.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Override
    public List<PositionDTO> getOpenPositions(Long tradingAccountId) {
        return positionRepository.findByTradingAccountIdAndStatus(
                tradingAccountId,
                Position.PositionStatus.OPEN
        ).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PositionDTO closePosition(Long positionId) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));
        
        position.setStatus(Position.PositionStatus.CLOSED);
        position.setClosedAt(LocalDateTime.now());
        Position savedPosition = positionRepository.save(position);
        log.info("Position closed: {}", positionId);
        return convertToDTO(savedPosition);
    }

    @Override
    public PositionDTO getPosition(Long positionId) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));
        return convertToDTO(position);
    }

    @Override
    @Transactional
    public void updateMarginAndPnL(Long positionId) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));
        
        if (position.getCurrentPrice() != null && position.getOpenPrice() != null) {
            BigDecimal pnl;
            if (position.getDirection() == com.maxvest.entity.Order.Direction.BUY) {
                pnl = position.getCurrentPrice().subtract(position.getOpenPrice())
                        .multiply(position.getVolume());
            } else {
                pnl = position.getOpenPrice().subtract(position.getCurrentPrice())
                        .multiply(position.getVolume());
            }
            position.setCurrentProfitLoss(pnl);
            positionRepository.save(position);
        }
    }

    private PositionDTO convertToDTO(Position position) {
        return PositionDTO.builder()
                .id(position.getId())
                .symbol(position.getSymbol())
                .direction(position.getDirection().toString())
                .volume(position.getVolume())
                .openPrice(position.getOpenPrice())
                .currentPrice(position.getCurrentPrice())
                .currentProfitLoss(position.getCurrentProfitLoss())
                .profitLossPercent(position.getProfitLossPercent())
                .status(position.getStatus().toString())
                .build();
    }
}
