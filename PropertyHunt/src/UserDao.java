import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    public static UserDao userDao = null;

    public static UserDao getInstance(){
        if(userDao == null)
            userDao = new UserDao();
        return userDao;
    }

    Map<String, Integer > userMap = new HashMap<>();
    Map<String, List<Property>> propertyListedMap = new HashMap<>();
    Map<String, List<Integer>> shortListedByUser = new HashMap<>();

    public User registerUser(String userName){
        if(userMap.containsKey(userName)) {
            System.out.println("User " + userName + " is already registered!!!");
            return null;
        }
        else{
            User user = new User();
            user.setUserName(userName);
            user.setUserId(IDGenerator.getId());
            userMap.put(userName, user.getUserId());
            System.out.println("User " + userName + " is successfully registered!!!");
            return user;
        }
    }

    public Property registerProperty(User user, String propertyName, String propertyLocation, Long propertyPrice, ListingType listingType, String propertySize, RoomType roomType, Status status){
        if(!propertyListedMap.containsKey(user.getUserName())) {
            List<Property>propertyListedByUser = propertyListedMap.get(user.getUserName());
            if(propertyListedByUser != null) {
                for (Property property : propertyListedByUser) {
                    if (property.getPropertyName() == propertyName && property.getPropertyLocation() == propertyLocation) {
                        System.out.println("Property is already registered !!!");
                        return null;
                    }
                }
            }
        }

        Property property = new Property();
        property.setPropertyId(IDGenerator.getId());
        property.setPropertyName(propertyName);
        property.setPropertyLocation(propertyLocation);
        property.setPropertyPrice(propertyPrice);
        property.setRoomType(roomType);
        property.setPropertySize(propertySize);
        property.setListingType(listingType);
        property.setStatus(status);

        if(!propertyListedMap.containsKey(user.getUserName())) {
            List<Property> propertyList = new ArrayList<>();
            propertyList.add(property);
            propertyListedMap.put(user.getUserName(), propertyList);
        }
        else
            propertyListedMap.get(user).add(property);
        System.out.println("Property is registered !!!");
        return property;
    }

    public List<Property> searchProperty(User user, String location){
        if(!userMap.containsKey(user.getUserName()))
            System.out.println("User " + user.getUserName() + " is not registered !!!");
        List<Property>matchedProperties = fetchAllProperties(location);
        return matchedProperties;
    }

    public List<Property> fetchAllProperties(String location){
        List<Property>allListedProperties = new ArrayList<>();
        for (Map.Entry<String,List<Property>> entry : propertyListedMap.entrySet()){
            allListedProperties.addAll(entry.getValue());
        }
        List<Property>matchedProperties = new ArrayList<>();
        for(Property property : allListedProperties){
            if(property.getPropertyLocation().equals(location)) {
                matchedProperties.add(property);
            }
        }
        return matchedProperties;
    }

    public void shortListProperty(User user, int propertyId){
        if(!shortListedByUser.containsKey(user.getUserName())) {
            List<Integer> shortListedProperties = new ArrayList<>();
            shortListedProperties.add(propertyId);
            System.out.println("Property is shortlisted!!!");
            shortListedByUser.put(user.getUserName(), shortListedProperties);
            return;
        }
        shortListedByUser.get(user.getUserName()).add(propertyId);
        System.out.println("Property is shortlisted!!!");
    }

    public void viewShortListed(User user){
        List<Integer>shortListedPropertyIds = shortListedByUser.get(user.getUserName());
        showAllShortListedProperties(shortListedPropertyIds);
    }

    public void showAllShortListedProperties(List<Integer> shortListedPropertyIds){
        List<Property>allListedProperties = new ArrayList<>();
        for (Map.Entry<String,List<Property>> entry : propertyListedMap.entrySet()){
            allListedProperties.addAll(entry.getValue());
        }
        System.out.println("Properties shortlisted ");
        System.out.println("Id    " + "Title    " + "Location    " + "Price    " + "Size    " + "Rooms   " + "Status");
        for(Integer id : shortListedPropertyIds) {
            for (Property property : allListedProperties) {
                if (property.getPropertyId() == id) {
                    System.out.println(property.getPropertyId() + "    " + property.getPropertyName() + "    " + property.getPropertyLocation() + "    " + property.getPropertyPrice() + "    " + property.getPropertySize() + "    " + property.getRoomType() + "    " + property.getStatus());
                }
            }
        }
    }

}
