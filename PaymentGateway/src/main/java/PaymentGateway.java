import model.Client;
import model.Payment;
import model.PaymentMode;
import model.PaymentRule;
import service.ClientServiceImpl;
import service.IClientService;

import java.util.UUID;

public class PaymentGateway {
    private static IClientService clientService = new ClientServiceImpl();

    public static void main(String[] args) {
        Client client1 = new Client(UUID.randomUUID(), "Flipkart");
        boolean addClientStatus1 = clientService.addClient(client1);
        Client client2 = new Client(UUID.randomUUID(), "Myntra");
        boolean addClientStatus2 = clientService.addClient(client2);

        clientService.removeClient(client2);

        clientService.hasClient(client2);

        clientService.listSupportedPaymodes();

        clientService.addSupportForPaymode(client1, PaymentMode.CARD);
        clientService.addSupportForPaymode(client1, PaymentMode.UPI);
        //clientService.removePaymode(client1, PaymentMode.UPI);

        PaymentRule rule1 = new PaymentRule();
        rule1.setPaymentRule(PaymentMode.CARD);
        Payment payment1 = new Payment(UUID.randomUUID(), PaymentMode.CARD, rule1);
        clientService.makePayment(client1, payment1);

        PaymentRule rule2 = new PaymentRule();
        rule2.setPaymentRule(PaymentMode.UPI);
        Payment payment2 = new Payment(UUID.randomUUID(), PaymentMode.UPI, rule2);
        clientService.makePayment(client1, payment2);

        Payment payment3 = new Payment(UUID.randomUUID(), PaymentMode.UPI, rule2);
        clientService.makePayment(client1, payment3);

        Payment payment4 = new Payment(UUID.randomUUID(), PaymentMode.CARD, rule2);
        clientService.makePayment(client1, payment4);

        clientService.addSupportForPaymode(client2, PaymentMode.CARD);
        clientService.addSupportForPaymode(client2, PaymentMode.UPI);
        clientService.makePayment(client2, payment1);
        clientService.makePayment(client2, payment2);

        clientService.showDistribution();

    }
}
