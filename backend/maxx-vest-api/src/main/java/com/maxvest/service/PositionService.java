package com.maxvest.service;

import com.maxvest.dto.PositionDTO;

import java.util.List;

public interface PositionService {
    List<PositionDTO> getOpenPositions(Long tradingAccountId);
    PositionDTO closePosition(Long positionId);
    PositionDTO getPosition(Long positionId);
    void updateMarginAndPnL(Long positionId);
}
