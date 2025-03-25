package com.rocket.sqsmicroservice.checkout.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.rocket.sqsmicroservice.checkout.producer.service.CheckoutService;

@RestController
@Slf4j
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/create-payment")
    public Mono<Void> createPayment() {
        log.info("Sending order to the queue");
        return checkoutService.sendToProcessOrderQueue()
                .doOnError(error -> log.error("Error ocurred when sending to the queue"));
    }
}
