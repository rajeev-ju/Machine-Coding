import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public class OrderService {
    private static OrderService orderService = null;

    public static OrderService getInstance(){
        if(orderService == null)
            orderService = new OrderService();
        return  orderService;
    }

    UserDao userDao = UserDao.getInstance();

    public Order placeOrder(User user, List<String>items, List<Integer>quantity, String selectionStrategey){
        if(items.isEmpty() || quantity.isEmpty()){
            System.out.println("Invalid order !!!");
            return null;
        }
        return userDao.placeOrder(user, items, quantity, selectionStrategey);
    }
}
