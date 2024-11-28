package example.tests.DB.User;

import example.model.DAO.UserDAO;
import example.model.entity.User;

public class FindTest {
    public static void main(String[] args) {
        User userToFind = new User("", "", "");
        UserDAO userDAO = new UserDAO(userToFind);
        System.out.println(userDAO.findById(6));
        System.out.println(userDAO.findByEmail("juan@gmail.com"));
    }
}
