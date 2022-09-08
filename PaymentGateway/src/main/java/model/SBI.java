package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
@Getter
@Setter
public class SBI implements Bank{
    private int numberOfPaymentRequest;

    public SBI() {
        numberOfPaymentRequest = (int)Math.floor(Math.random()*(10) + 1);
    }

    @Override
    public boolean makePayment() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
