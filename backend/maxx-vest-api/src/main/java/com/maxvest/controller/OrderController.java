package com.maxvest.controller;

import com.maxvest.dto.OrderDTO;
import com.maxvest.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Order management endpoints")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create order")
    public ResponseEntity<OrderDTO> createOrder(
            @RequestParam Long accountId,
            @Valid @RequestBody OrderDTO orderDTO) {
        log.info("Creating order for account: {}", accountId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(accountId, orderDTO));
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get order by ID")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping
    @Operation(summary = "Get account orders")
    public ResponseEntity<List<OrderDTO>> getOrders(@RequestParam Long accountId) {
        return ResponseEntity.ok(orderService.getAccountOrders(accountId));
    }

    @PutMapping("/{orderId}")
    @Operation(summary = "Update order")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, orderDTO));
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "Cancel order")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
