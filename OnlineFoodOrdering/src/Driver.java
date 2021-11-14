import java.util.ArrayList;
import java.util.Arrays;

public class Driver {
    public static void main(String[] args){
        UserService userService = UserService.getInstance();
        RestaurantService restaurantService = RestaurantService.getInstance();
        OrderService orderService = OrderService.getInstance();

        User user1 = userService.registerUser("Ram",8884446546L);
        User user2 = userService.registerUser("Shyam", 6768987654L);

        Restaurant r1 = restaurantService.registerRestaurant("Chinese Dhaba", new ArrayList<String>(Arrays.asList("Veg Biryani", "Chicken Biryani")), new ArrayList<Integer>(Arrays.asList(100, 120)), 5, 4.5 );
        if(r1 != null)
            System.out.println(r1.toString());
//        Restaurant r2 = restaurantService.registerRestaurant("Indian Dhaba", new ArrayList<String>(Arrays.asList("Palak Paneer", "Chicken Dopyaja")), new ArrayList<Integer>(Arrays.asList(80, 140)), 5, 5.0 );
//        if(r2 != null)
//            System.out.println(r2.toString());

        Restaurant r3 = restaurantService.registerRestaurant("Indian Dhaba", new ArrayList<String>(Arrays.asList("Veg Biryani", "Chicken Biryani")), new ArrayList<Integer>(Arrays.asList(80, 140)), 1, 5.0 );
        if(r3 != null)
            System.out.println(r3.toString());
//        Order order1 = orderService.placeOrder(user1, new ArrayList<String>(Arrays.asList("Palak Paneer", "Chicken Dopyaja")), new ArrayList<Integer>(Arrays.asList(1, 2)), "ByRating");
//        if(order1 != null)
//            System.out.println(order1.toString());
        Order order2 = orderService.placeOrder(user1, new ArrayList<String>(Arrays.asList("Veg Biryani", "Chicken Biryani")), new ArrayList<Integer>(Arrays.asList(1, 2)), "ByRating");
        if(order2 != null)
            System.out.println(order2.toString());
        restaurantService.updateCapacity(order2);
        Order order3 = orderService.placeOrder(user1, new ArrayList<String>(Arrays.asList("Veg Biryani", "Chicken Biryani")), new ArrayList<Integer>(Arrays.asList(1, 2)), "ByRating");
        if(order3 != null)
            System.out.println(order3.toString());
        restaurantService.updateCapacity(order3);
        Order order4 = orderService.placeOrder(user1, new ArrayList<String>(Arrays.asList("Veg Biryani", "Chicken Biryani")), new ArrayList<Integer>(Arrays.asList(1, 2)), "ByRating");
        if(order4 != null)
            System.out.println(order4.toString());

        //Restaurant r3 = restaurantService.updateMenu("Chinese Dhaba", new ArrayList<String>(Arrays.asList("Palak Paneer", "Chicken Dopyaja")), new ArrayList<Integer>(Arrays.asList(80, 140)));
    }
}
