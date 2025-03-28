package com.rocket.sqsmicroservice.checkout.producer.controller;

import com.rocket.sqsmicroservice.checkout.producer.model.CheckoutMessage;
import com.rocket.sqsmicroservice.checkout.producer.service.CheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private static final Logger log = LoggerFactory.getLogger(CheckoutController.class);

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/process-order")
    public Mono<ResponseEntity<String>> sendToProcessOrderQueue(@RequestBody CheckoutMessage checkoutMessage) {
        log.info("Received request to send message to Process Order Queue: {}", checkoutMessage);
        return checkoutService.sendToProcessOrderQueue(checkoutMessage)
                .thenReturn(ResponseEntity.ok("Message sent to Process Order Queue"));
    }

    @PostMapping("/inventory-update")
    public Mono<ResponseEntity<String>> sendToInventoryUpdateQueue(@RequestBody CheckoutMessage checkoutMessage) {
        log.info("Received request to send message to Inventory Update Queue: {}", checkoutMessage);
        return checkoutService.sendToInventoryUpdateQueue(checkoutMessage)
                .thenReturn(ResponseEntity.ok("Message sent to Inventory Update Queue"));
    }

    @PostMapping("/payment-processing")
    public Mono<ResponseEntity<String>> sendToPaymentProcessingQueue(@RequestBody CheckoutMessage checkoutMessage) {
        log.info("Received request to send message to Payment Processing Queue: {}", checkoutMessage);
        return checkoutService.sendToPaymentProcessingQueue(checkoutMessage)
                .thenReturn(ResponseEntity.ok("Message sent to Payment Processing Queue"));
    }

    @PostMapping("/shipping-notification")
    public Mono<ResponseEntity<String>> sendToShippingNotificationQueue(@RequestBody CheckoutMessage checkoutMessage) {
        log.info("Received request to send message to Shipping Notification Queue: {}", checkoutMessage);
        return checkoutService.sendToShippingNotificationQueue(checkoutMessage)
                .thenReturn(ResponseEntity.ok("Message sent to Shipping Notification Queue"));
    }
}
