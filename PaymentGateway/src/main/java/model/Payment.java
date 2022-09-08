package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Payment {
    UUID paymentId;
    PaymentMode paymentMode;
    PaymentRule paymentRule;
    PaymentStatus paymentStatus;

    public Payment(UUID paymentId, PaymentMode paymentMode, PaymentRule paymentRule) {
        this.paymentId = paymentId;
        this.paymentMode = paymentMode;
        this.paymentRule = paymentRule;
        this.paymentStatus = PaymentStatus.NOTINITIATED;
    }
}
