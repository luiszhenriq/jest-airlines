package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.payment.PaymentResponse;
import br.com.luis.jest_airlines.service.PaymentService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{id}")
    public ResponseEntity<PaymentResponse> createPayment(@PathVariable("id") UUID id) throws StripeException {
        PaymentResponse response = paymentService.createPayment(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
