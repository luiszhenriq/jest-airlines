package br.com.luis.jest_airlines.model.enums;

public enum ReservationStatus {

    CONFIRMADO("confirmado"),
    PENDENTE("pendente"),
    CANCELADO("cancelado");

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }
}
