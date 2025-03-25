package com.rocket.sqsmicroservice.checkout.producer.sqs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.Map;

import static com.rocket.sqsmicroservice.checkout.producer.utils.Json.toJson;

@Slf4j
@Service
@RequiredArgsConstructor
public class SqsPublisher {

    private final SqsAsyncClient sqsAsyncClient;

    public <T>Mono<SendMessageResponse> sendToProcessOrderQueue(final T object, final Map<String, MessageAttributeValue> messageAttributes, final String queue) {
        final String messageBody = toJson(object);
        return sendToProcessOrderQueue(messageBody, messageAttributes, queue);
    }

    public Mono<SendMessageResponse> sendToProcessOrderQueue(final String message, final Map<String, MessageAttributeValue> messageAttributes, final String queue) {
        log.info("Sending message to the sqs queue");

        final SendMessageRequest request = SendMessageRequest.builder().queueUrl(queue).messageAttributes(messageAttributes).messageBody(message).build();
        return Mono.fromFuture(sqsAsyncClient.sendMessage(request))
                .doOnSuccess(result -> log.info("Message send with success to the queue"))
                .doOnError(error -> log.error("message with error in the queue"));
    }
}
