import java.util.Scanner;

public class DriverClass {
    public static void main(String args[]){
        System.out.println("Welcome to Property Hunt");
        //System.out.println("To register yourself, please enter your name");
        //Scanner sc = new Scanner();
        UserService userService = UserService.getInstance();
        PropertyService propertyService = PropertyService.getInstance();
        User user1 = userService.registerUser("Jack");
        //User user2 =
        //userService.registerUser("Jack");
        propertyService.registerProperty(user1, "3BHK for sale", "Bellandur", 90L, ListingType.SELL, "1800 sqft", RoomType.THREEBHK, Status.AVAILABLE);
        propertyService.searchProperty(user1, "Bellandur");
        userService.shortListProperty(user1, 2);
        userService.viewShortListed(user1);

    }
}
