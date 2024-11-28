package example.tests.DB.User;

import example.model.DAO.UserDAO;
import example.model.entity.User;

public class InsertTest {
    public static void main(String[] args) {
        User userToInsert = new User("juan", "1234", "juan@gmail.com");
        UserDAO userDAO = new UserDAO(userToInsert);
        System.out.println(userDAO.save());
        System.out.println(userDAO);
    }
}
