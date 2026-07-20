package ua.pp.darknsoft.darkystore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.darkystore.dto.OrderRequestDto;

@RestController
@RequestMapping("/api/v1/stripe")
@RequiredArgsConstructor
public class StripeWebhookController {

    @PostMapping("/webhook")
    public ResponseEntity<String> createOrder(@RequestHeader("Stripe-Signature") String sigHeader) {
        if (!sigHeader.equals("Secret")) {
            System.out.println("Stripe-Signature: " + sigHeader);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        System.out.println("Order created successfully!");
        return ResponseEntity.ok("Order created successfully!");
    }
}
