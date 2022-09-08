package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRule {
    Bank bank;
    HDFC hdfcbank;
    ICICI icicibank;
    SBI sbibank;

    public PaymentRule() {
        this.hdfcbank = new HDFC();
        this.icicibank = new ICICI();
        this.sbibank = new SBI();
    }

    public void setPaymentRule(PaymentMode paymentMode) {
        if(paymentMode.equals(PaymentMode.UPI)) {
            if(hdfcbank.getNumberOfPaymentRequest() < icicibank.getNumberOfPaymentRequest())
                this.bank = hdfcbank;
            else
                this.bank = icicibank;
        }
        else if(paymentMode.equals(PaymentMode.CARD)){
            if(sbibank.getNumberOfPaymentRequest() < icicibank.getNumberOfPaymentRequest())
                this.bank = sbibank;
            else
                this.bank = icicibank;
        }

    }
}
