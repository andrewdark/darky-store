package ua.pp.darknsoft.darkystore.service;

import ua.pp.darknsoft.darkystore.dto.PaymentIntentRequestDto;
import ua.pp.darknsoft.darkystore.dto.PaymentIntentResponseDto;

public interface IPaymentService {
    PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto requestDto);
}
