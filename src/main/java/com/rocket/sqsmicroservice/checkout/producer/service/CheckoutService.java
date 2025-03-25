package com.rocket.sqsmicroservice.checkout.producer.service;

import com.rocket.sqsmicroservice.checkout.producer.configuration.AwsConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.rocket.sqsmicroservice.checkout.producer.model.CheckoutMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import com.rocket.sqsmicroservice.checkout.producer.sqs.SqsPublisher;

import java.util.Random;
import java.util.UUID;

import static java.util.Collections.emptyMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckoutService {

    private final SqsPublisher sqsPublisher;
    private final AwsConfiguration awsConfiguration;
    private final OrderService orderService;

    public Mono<Void> sendToProcessOrderQueue() {
        return orderService.findOrderById(UUID.randomUUID().toString())
                .flatMap(validateOrder -> {
                    final CheckoutMessage checkoutMessage = CheckoutMessage.builder()
                            .orderId(UUID.randomUUID().toString())
                            .sellerId(UUID.randomUUID().toString())
                            .amount(new Random().nextLong()).build();

                    return sqsPublisher.sendToProcessOrderQueue(
                            checkoutMessage, emptyMap(), awsConfiguration.getProcessOrderQueueUrl()
                    );
                }).switchIfEmpty(Mono.defer(() -> {
                    log.warn("didnt work");
                    return Mono.just(SendMessageResponse.builder().build());
        })).then();
    }
}
