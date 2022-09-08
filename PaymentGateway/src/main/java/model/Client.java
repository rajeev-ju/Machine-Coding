package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Client {
    UUID clientId;
    String clientName;
    List<PaymentMode>supportedPaymentModes;

    public Client(UUID clientId, String clientName) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.supportedPaymentModes = new ArrayList<>();
    }
}
