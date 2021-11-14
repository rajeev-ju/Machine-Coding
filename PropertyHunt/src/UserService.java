public class UserService {
    public static UserService userService = null;

    public static UserService getInstance(){
        if(userService == null)
            userService = new UserService();
        return userService;
    }

    UserDao userDao = UserDao.getInstance();

    public User registerUser(String userName){
        if(!userName.isEmpty()) {
            User user = userDao.registerUser(userName);
            return user;
        }
        else {
            System.out.println("UserName is invalid!!!");
            return null;
        }
    }

    public void shortListProperty(User user, int propertyId){
        userDao.shortListProperty(user, propertyId);
    }

    public void viewShortListed(User user){
        userDao.viewShortListed(user);
    }
}
