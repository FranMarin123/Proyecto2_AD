package example.model.Singleton;

import example.model.entity.User;

public class UserSigned {
    private static UserSigned _instance;
    private User currentUser;

    private UserSigned(User user){
        currentUser=user;
    }

    public static UserSigned getInstance(){
        return _instance;
    }

    public static UserSigned getInstance(User user){
        if (user!=null){
            _instance=new UserSigned(user);
        }
        return _instance;
    }

    public static void close(){
        _instance=null;
    }

    public User getCurrentUser(){
        return currentUser;
    }
}
