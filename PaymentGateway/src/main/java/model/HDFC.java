package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class HDFC implements Bank{
    private int numberOfPaymentRequest;

    public HDFC() {
        numberOfPaymentRequest = (int)Math.floor(Math.random()*(10) + 1);
    }

    @Override
    public boolean makePayment() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
