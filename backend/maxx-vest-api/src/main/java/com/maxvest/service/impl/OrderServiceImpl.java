package com.maxvest.service.impl;

import com.maxvest.dto.OrderDTO;
import com.maxvest.entity.Order;
import com.maxvest.entity.TradingAccount;
import com.maxvest.exception.ResourceNotFoundException;
import com.maxvest.repository.OrderRepository;
import com.maxvest.repository.TradingAccountRepository;
import com.maxvest.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TradingAccountRepository tradingAccountRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(Long tradingAccountId, OrderDTO orderDTO) {
        log.info("Creating order for account: {}", tradingAccountId);
        TradingAccount account = tradingAccountRepository.findById(tradingAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Trading account not found"));

        Order order = Order.builder()
                .tradingAccount(account)
                .symbol(orderDTO.getSymbol())
                .orderType(Order.OrderType.valueOf(orderDTO.getOrderType()))
                .direction(Order.Direction.valueOf(orderDTO.getDirection()))
                .volume(orderDTO.getVolume())
                .openPrice(orderDTO.getOpenPrice())
                .stopLoss(orderDTO.getStopLoss())
                .takeProfit(orderDTO.getTakeProfit())
                .status(Order.OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        
        order.setStopLoss(orderDTO.getStopLoss());
        order.setTakeProfit(orderDTO.getTakeProfit());
        orderRepository.save(order);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return convertToDTO(order);
    }

    @Override
    public List<OrderDTO> getAccountOrders(Long tradingAccountId) {
        return orderRepository.findByTradingAccountId(tradingAccountId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setStatus(Order.OrderStatus.CANCELLED);
        orderRepository.save(order);
        log.info("Order cancelled: {}", orderId);
    }

    @Override
    @Transactional
    public void executeOrder(Long orderId, BigDecimal executionPrice) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setStatus(Order.OrderStatus.FILLED);
        order.setExecutionPrice(executionPrice);
        order.setExecutedAt(LocalDateTime.now());
        orderRepository.save(order);
        log.info("Order executed: {} at price: {}", orderId, executionPrice);
    }

    private OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .symbol(order.getSymbol())
                .orderType(order.getOrderType().toString())
                .direction(order.getDirection().toString())
                .volume(order.getVolume())
                .openPrice(order.getOpenPrice())
                .stopLoss(order.getStopLoss())
                .takeProfit(order.getTakeProfit())
                .status(order.getStatus().toString())
                .commission(order.getCommission())
                .build();
    }
}
