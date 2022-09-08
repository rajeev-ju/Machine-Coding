package service;

import model.Client;
import model.Payment;
import model.PaymentMode;

public interface IClientService {
    boolean addClient(Client client);
    void removeClient(Client client);
    void hasClient(Client client);
    void addSupportForPaymode(Client client, PaymentMode paymentMode);
    void removePaymode(Client client, PaymentMode paymentMode);
    void makePayment(Client client, Payment payment);
    void showDistribution();
    void listSupportedPaymodes();
}

