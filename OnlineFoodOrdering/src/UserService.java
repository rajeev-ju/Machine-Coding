public class UserService {
    private static UserService userService = null;

    public static UserService getInstance(){
        if(userService == null)
            userService = new UserService();
        return userService;
    }

    UserDao userDao = UserDao.getInstance();

    public User registerUser(String name, Long phone){
        if(phone == null || phone <= 0){
            System.out.println("Invalid phone  number !!!");
            return null;
        }
        else if(name.isEmpty()){
            System.out.println("Username is invalid !!!");
            return  null;
        }
        return userDao.registerUser(name, phone);
    }
}
