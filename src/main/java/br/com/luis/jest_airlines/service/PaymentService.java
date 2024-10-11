package br.com.luis.jest_airlines.service;


import br.com.luis.jest_airlines.dto.payment.PaymentResponse;
import br.com.luis.jest_airlines.dto.reservation.ReservationResponseDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final String secretKey = System.getenv("STRIPE_SECRET_KEY");

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    private final ReservationService reservationService;

    public PaymentResponse createPayment(UUID id) throws StripeException {

        ReservationResponseDTO reservation = reservationService.findById(id);

        SessionCreateParams params =
                SessionCreateParams.builder().addPaymentMethodType
                                (SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:8080/payment/success")
                        .setCancelUrl("http://localhost:8080/payment/failed")
                        .setPaymentIntentData(SessionCreateParams.PaymentIntentData.builder()
                                .putMetadata("reservation_id", String.valueOf(reservation.id())).build())
                        .addLineItem(SessionCreateParams.LineItem.builder()
                                .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("brl")
                                        .setUnitAmount((long) reservation.value())
                                        .setProductData(SessionCreateParams.LineItem.PriceData.
                                                ProductData.builder().setName("Passagem aérea de Salvador para São Paulo").build())
                                        .build()

                                ).
                                build()
                        )
                        .build();
        Session session = Session.create(params);
        PaymentResponse response = new PaymentResponse();
        response.setPayment_url(session.getUrl());

        return response;
    }


}
