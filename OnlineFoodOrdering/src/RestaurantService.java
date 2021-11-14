import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static RestaurantService restaurantService = null;

    public static RestaurantService getInstance(){
        if(restaurantService == null)
            restaurantService = new RestaurantService();
        return restaurantService;
    }

    UserDao userDao = UserDao.getInstance();

    public Restaurant registerRestaurant(String name, List<String>items, List<Integer>pricesOfItems, int maxNumberOfOrdersServedAt1Tiem, Double rating){
        if(items.isEmpty() || pricesOfItems.isEmpty() || items.size() != pricesOfItems.size()){
            System.out.println("Invalid value of Items and Prices");
            return null;
        }
        else if(name.isEmpty()){
            System.out.println("Restaurant name cannot be empty");
            return null;
        }

        return userDao.registerRestaurant(name, items, pricesOfItems, maxNumberOfOrdersServedAt1Tiem, rating);
    }

    public Restaurant updateMenu(String name, List<String> newItems, List<Integer>newPrices){
        if(newItems.isEmpty() || newPrices.isEmpty() || newItems.size() != newPrices.size()){
            System.out.println("Invalid items and prices, Menu cannot be updated");
            return null;
        }
        return userDao.updateMenu(name, newItems, newPrices);
    }

    public Restaurant updateCapacity(Order order){
        return userDao.updateCapacity(order);
    }

    public List<Restaurant> showRestaurant(String sortBy){
        //return userDao.showRestaurant(sortBy);
        return new ArrayList<>();
    }

}
