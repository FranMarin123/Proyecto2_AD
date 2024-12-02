package example.tests.DB.Library;

import example.model.DAO.LibraryDAO;
import example.model.DAO.UserDAO;
import example.model.entity.Library;
import example.model.entity.User;

public class InsertTest {
    public static void main(String[] args) {
        User user= UserDAO.findByEmail("pedro@gmail.com");
        Library library=new Library(user);
        LibraryDAO libraryDAO=new LibraryDAO(library);
        System.out.println(libraryDAO.save());
    }
}
