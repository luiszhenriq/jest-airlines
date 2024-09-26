package br.com.luis.jest_airlines.model.enums;

public enum PaymentMethod {

    PIX("pix"),
    DEBITO("debito"),
    CREDITO("credito");

    private String paymentMethod;

    PaymentMethod(String paymentMethod)  {
        this.paymentMethod = paymentMethod;
    }
}
