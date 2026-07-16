package com.maxvest.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MarketDataWebSocketHandler {

    @MessageMapping("/market/subscribe")
    @SendTo("/topic/market-data")
    public Map<String, Object> handleMarketDataSubscription(String symbol) {
        log.info("Client subscribed to: {}", symbol);
        Map<String, Object> response = new HashMap<>();
        response.put("type", "SUBSCRIPTION_CONFIRMED");
        response.put("symbol", symbol);
        return response;
    }

    @MessageMapping("/market/unsubscribe")
    public void handleMarketDataUnsubscription(String symbol) {
        log.info("Client unsubscribed from: {}", symbol);
    }
}
