import java.util.*;

public class UserDao {
    private static UserDao userDao = null;

    public static UserDao getInstance(){
        if(userDao == null)
            userDao = new UserDao();
        return userDao;
    }

    private HashMap<Integer, User> userHashMap = new HashMap<>();
    private HashMap<Long, Integer> phoneNumberMap = new HashMap<>();
    private HashMap<String, Restaurant> restaurantNameMap = new HashMap<>();

    public User registerUser(String name, Long phone){
        if(phoneNumberMap.containsKey(phone)){
            System.out.println("User is already registered!!!");
            return null;
        }

        User user = new User(IDGenerator.getId(), name, phone);
        userHashMap.put(user.getId(), user);
        System.out.println("user is successfully registered!!!");
        return user;
    }

    public Restaurant registerRestaurant(String name, List<String>items, List<Integer>pricesOfItems, int maxNumberOfOrdersServedAt1Tiem, double rating){
        if(restaurantNameMap.containsKey(name)){
            System.out.println("This restaurant is already registered!!!");
            return null;
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setId(IDGenerator.getId());
        restaurant.setName(name);
        restaurant.setItems(items);
        restaurant.setItemsPrices(pricesOfItems);
        restaurant.setMaxNumberOfOrdersServedAt1Time(maxNumberOfOrdersServedAt1Tiem);
        restaurant.setRating(rating);
        restaurantNameMap.put(name, restaurant);
        System.out.println("Restaurant is successfully registered!!!");
        return restaurant;
    }

    public Restaurant updateMenu(String name, List<String>newItems, List<Integer>newPrices){
        Restaurant restaurant = restaurantNameMap.get(name);
        if(restaurant == null){
            System.out.println("This restaurant is not registered!!!");
            return null;
        }

        List<String> existingMenu = restaurant.getItems();
        List<Integer> existingPrice = restaurant.getItemsPrices();
        List<Integer> updatedPrices = new ArrayList<>();
        List<String> updatedMenu = new ArrayList<>();
        boolean flag = false;
        for(int i = 0; i < newItems.size(); i++) {
            flag = false;
            for (int j = 0; j < existingMenu.size(); j++) {
                if (existingMenu.get(j).equals(newItems.get(i))){
                    updatedMenu.add(newItems.get(i));
                    updatedPrices.add(newPrices.get(i));
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                updatedMenu.add(newItems.get(i));
                updatedPrices.add(newPrices.get(i));
            }
        }
        for(int i = 0; i < existingMenu.size(); i++){
            if(!updatedMenu.contains(existingMenu.get(i))){
                updatedMenu.add(existingMenu.get(i));
                updatedPrices.add(existingPrice.get(i));
            }
        }
        restaurant.setItems(updatedMenu);
        restaurant.setItemsPrices(updatedPrices);
        System.out.println("Menu is successfully updated");
        System.out.println("New Menu is :-");
        for(String item : updatedMenu)
            System.out.println(item);
        return restaurant;
    }

    public Order placeOrder(User user, List<String>items, List<Integer> quantity, String selectionStrategey){
        List<Restaurant> restaurants = new ArrayList<>();
        for(Map.Entry<String, Restaurant> entry: restaurantNameMap.entrySet()){
            restaurants.add(entry.getValue());
        }
        if(selectionStrategey.equals("ByRating")) {
            Collections.sort(restaurants, new SortByRating());
            System.out.println("After sorting restaurants by Rating, the restaurants are in order :-");
            for(Restaurant restaurant : restaurants)
                System.out.println(restaurant);
        }
        else if(selectionStrategey.equals("ByLowestPrice"))
            Collections.sort(restaurants, new SortByRating());
        int count = 0;
        int restaurant_id = 0;
        //System.out.println(restaurants.get(0).toString());
        for(int i = 0; i < restaurants.size(); i++) {
            count = 0;
            for (String item : items) {
                for (String restaurantItem : restaurants.get(i).getItems()) {
                    if (restaurantItem.equals(item)) {
                        count++;
                        break;
                    }
                }
            }
            if(count == items.size() && restaurants.get(i).getMaxNumberOfOrdersServedAt1Time() != 0) {
                restaurant_id = i;
                break;
            }
        }
        if(count != items.size()) {
            System.out.println("All items are not present in the restaurant.");
            return null;
        }
        else if(count == items.size() && restaurants.get(restaurant_id).getMaxNumberOfOrdersServedAt1Time() == 0){
            System.out.println("Restaurant can't accept oreder right now. Its maximum capacity of serving orders is full");
            return null;
        }
        else{
            System.out.println("Order placed successfully");
            Order order = new Order();
            order.setId(IDGenerator.getId());
            order.setItems(items);
            order.setQuantity(quantity);
            order.setOrderStatus(OrderStatus.ACCEPTED);
            order.setOrderAcceptedBy(restaurants.get(restaurant_id).getName());
            restaurants.get(restaurant_id).setMaxNumberOfOrdersServedAt1Time(restaurants.get(0).getMaxNumberOfOrdersServedAt1Time() - 1);
            System.out.println("Order accepted by restaurant :- "+ restaurants.get(restaurant_id).toString());
            return order;
        }

    }

    public Restaurant updateCapacity(Order order){
        String restauran_name = order.getOrderAcceptedBy();
        Restaurant restaurant = restaurantNameMap.get(restauran_name);
        if(restaurant != null) {
            restaurant.setMaxNumberOfOrdersServedAt1Time(restaurant.getMaxNumberOfOrdersServedAt1Time() + 1);
            order.setOrderStatus(OrderStatus.COMPLETED);
        }
        return restaurant;
    }

    class SortByRating implements Comparator<Restaurant>{
        @Override
        public int compare(Restaurant r1, Restaurant r2){
            if(r1.getRating() < r2.getRating())
                return 1;
            else if(r1.getRating() == r2.getRating())
                return 0;
            else
                return -1;
        }
    }

//    class SortByPrice implements Comparator<Restaurant>{
//        @Override
//        public int compare(Restaurant r1, Restaurant r2){
//            int total1 = 0;
//            for(int price : r1.getItemsPrices())
//                total1 += price;
//            int total2 = 0;
//            for(int price : r2.getItemsPrices())
//                total2 += price;
//            return total1 - total2;
//        }
//    }
}
