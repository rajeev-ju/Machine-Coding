package service;

import model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientServiceImpl implements IClientService{
    Map<UUID, Client>clientMap;
    Router router;

    public ClientServiceImpl() {
        this.clientMap = new HashMap<>();
        this.router = new Router();
    }


    /***
     *
     * @param client - client is the object which contains all the details e.g, Flipkart, myntra
     * @return boolean - If False client is already presetn else client is added.
     */
    @Override
    public boolean addClient(Client client) throws RuntimeException{
        if(clientMap.containsKey(client.getClientId())){
            //System.out.println(client.getClientName() + " as aClient already exists..."); // we should use logger
            return false;
        }
        try {
            clientMap.put(client.getClientId(), client);
        }catch (Exception ex){
            throw new RuntimeException("Connection issue with DB");
        }
        //System.out.println(client.getClientName() + " as a Client added...");  // we should use logger
        return true;
    }

    @Override
    public void removeClient(Client client) {
        if(!clientMap.containsKey(client.getClientId())){
            System.out.println(client.getClientName() + " as a Client doesn't exists...");
        }
        clientMap.remove(client.getClientId());
        System.out.println(client.getClientName() + " as a Client removed...");
    }

    public void hasClient(Client client){
        if(!clientMap.containsKey(client.getClientId())){
            System.out.println(client.getClientName() + " as a Client doesn't exists...");
        }
        else
            System.out.println(client.getClientName() + " as a Client exists...");
    }

    public void addSupportForPaymode(Client client, PaymentMode paymentMode){
        if(clientMap.containsKey(client.getClientId())) {
            if (client.getSupportedPaymentModes().contains(paymentMode))
                System.out.println(paymentMode + " is already supported for " + client.getClientName());
            else {
                client.getSupportedPaymentModes().add(paymentMode);
                System.out.println(paymentMode + " paymentmode is supported now for " + client.getClientName());
            }
        }
        else{
                System.out.println(client.getClientName() + " is not onboarded to out system. Please onboard before making any transaction...");
            }

    }

    public void removePaymode(Client client, PaymentMode paymentMode){
        if(!client.getSupportedPaymentModes().contains(paymentMode))
            System.out.println(paymentMode + " is not supported...");
        else {
            client.getSupportedPaymentModes().remove(paymentMode);
            System.out.println(paymentMode + " paymentmode is removed now...");
        }
    }

    @Override
    public void makePayment(Client client, Payment payment) {
        if(!client.getSupportedPaymentModes().contains(payment.getPaymentMode()))
            System.out.println("This payment cannot be done because payment is not supported..");
        else{
            Bank bank = payment.getPaymentRule().getBank();
            boolean status = router.routePayment(payment);
            //boolean status = bank.makePayment();

            if(status) {
                System.out.println("Payment success through " + payment.getPaymentRule().getBank().getClass().getSimpleName());
                payment.setPaymentStatus(PaymentStatus.SUCCESS);
            }
            else {
                System.out.println("Payment failure through " + payment.getPaymentRule().getBank().getClass().getSimpleName());
                payment.setPaymentStatus(PaymentStatus.FAILURE);
            }
        }
    }

    @Override
    public void showDistribution(){
        router.showDistribution();
    }

    @Override
    public void listSupportedPaymodes(){
        PaymentMode[] paymentModes = PaymentMode.class.getEnumConstants();
        System.out.println(Arrays.stream(paymentModes).toArray());
    }
}
