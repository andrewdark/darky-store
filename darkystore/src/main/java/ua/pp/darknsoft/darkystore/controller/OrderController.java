package ua.pp.darknsoft.darkystore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.darknsoft.darkystore.dto.OrderRequestDto;
import ua.pp.darknsoft.darkystore.service.IOrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService iOrderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto requestDto) {
        iOrderService.createOrder(requestDto);
        return ResponseEntity.ok("Order created successfully!");
    }
}
