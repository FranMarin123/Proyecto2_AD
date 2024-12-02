package example.tests.DB.Library;

import example.model.DAO.LibraryDAO;
import example.model.DAO.LibraryVideoDAO;
import example.model.DAO.UserDAO;
import example.model.entity.Library;
import example.model.entity.User;

public class FillLibrary {
    public static void main(String[] args) {
        User user= UserDAO.findByEmail("pedro@gmail.com");
        Library library= LibraryDAO.findByOwner(user);
        System.out.println((LibraryVideoDAO.findAllVideosForLibrary(library)));

    }
}
