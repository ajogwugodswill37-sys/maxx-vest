package com.maxvest.service;

import com.maxvest.dto.OrderDTO;
import com.maxvest.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(Long tradingAccountId, OrderDTO orderDTO);
    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);
    OrderDTO getOrder(Long orderId);
    List<OrderDTO> getAccountOrders(Long tradingAccountId);
    void cancelOrder(Long orderId);
    void executeOrder(Long orderId, BigDecimal executionPrice);
}
