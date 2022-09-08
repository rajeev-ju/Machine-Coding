package service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.Bank;
import model.Payment;
import model.PaymentRule;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Router {
    private Map<Bank, Integer> routeRquestMap;

    public Router() {
        this.routeRquestMap = new HashMap<>();
    }

     public boolean routePayment(Payment payment){
        PaymentRule paymentRule = payment.getPaymentRule();
        Bank paymentBank = paymentRule.getBank();
        if(routeRquestMap.containsKey(paymentBank))
            this.routeRquestMap.put(paymentBank, routeRquestMap.get(paymentBank) + 1);
        else
            this.routeRquestMap.put(paymentBank, 1);
        return paymentRule.getBank().makePayment();
    }

    public void showDistribution(){
        int totalRequest = this.routeRquestMap.values().stream().mapToInt(Integer :: intValue).sum();
        routeRquestMap.forEach(((bank, integer) -> {
            System.out.println("Distribution of payment request on " + bank.getClass().getSimpleName() + " is " + (double)(integer) / (double) (totalRequest) * 100 + " %");
        }));
    }
}
