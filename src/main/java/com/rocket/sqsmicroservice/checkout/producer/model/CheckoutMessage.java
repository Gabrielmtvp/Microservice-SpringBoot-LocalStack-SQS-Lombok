package com.rocket.sqsmicroservice.checkout.producer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutMessage {
    private String orderId;
    private String sellerId;
    private Long amount;
}
