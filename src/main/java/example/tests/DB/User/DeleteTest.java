package example.tests.DB.User;

import example.model.DAO.UserDAO;
import example.model.entity.User;

public class DeleteTest {
    public static void main(String[] args) {
        User userToDelete = new User("", "", "juan@gmail.com");
        UserDAO userDAO = new UserDAO(userToDelete);
        System.out.println(userDAO.delete());
        System.out.println(userDAO);
    }
}
