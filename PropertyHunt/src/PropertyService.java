import javax.jws.soap.SOAPBinding;
import java.util.List;

public class PropertyService {
    public static PropertyService propertyService = null;

    public static PropertyService getInstance(){
        if(propertyService == null)
            propertyService = new PropertyService();
        return propertyService;
    }

    UserDao userDao = UserDao.getInstance();

    public Property registerProperty(User user, String propertyName, String propertyLocation, Long propertyPrice, ListingType listingType, String propertySize, RoomType roomType, Status status){
        Property property = userDao.registerProperty(user, propertyName,  propertyLocation,  propertyPrice, listingType, propertySize, roomType, status);
        return property;
    }

    public List<Property> searchProperty(User user, String location){
        List<Property>shortListedProperty = userDao.searchProperty(user, location);
        if(shortListedProperty.isEmpty())
            System.out.println("No property matches to your requirement!!!");
        else {
            System.out.println("Property matching to your requirement are : ");
            System.out.println("Id    " + "Title    " + "Location    " + "Price    " + "Size    " + "Rooms   " + "Status");
            for (Property property : shortListedProperty)
                System.out.println(property.getPropertyId() + "    " + property.getPropertyName() + "    " + property.getPropertyLocation() + "    " + property.getPropertyPrice() + "    " + property.getPropertySize() + "    " + property.getRoomType() + "    " + property.getStatus());
        }
        return shortListedProperty;
    }
}
