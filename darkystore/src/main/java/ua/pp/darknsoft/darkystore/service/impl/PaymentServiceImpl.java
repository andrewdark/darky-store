package ua.pp.darknsoft.darkystore.service.impl;

import com.stripe.Stripe;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.darkystore.dto.PaymentIntentRequestDto;
import ua.pp.darknsoft.darkystore.dto.PaymentIntentResponseDto;
import ua.pp.darknsoft.darkystore.service.IPaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Override
    public PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto requestDto) {
        try {
            //Stripe.apiKey = ""; // - set in the StripeConfig

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(requestDto.amount())
                    .setCurrency(requestDto.currency())
                    .addPaymentMethodType("card").build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return new PaymentIntentResponseDto(paymentIntent.getClientSecret());
        } catch (StripeException e) {
            throw new RuntimeException("Failed to create payment intent", e);
        }

    }
}
