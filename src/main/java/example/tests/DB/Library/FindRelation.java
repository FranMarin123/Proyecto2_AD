package example.tests.DB.Library;

import example.model.DAO.LibraryDAO;
import example.model.DAO.LibraryVideoDAO;
import example.model.DAO.UserDAO;
import example.model.DAO.VideoDAO;
import example.model.entity.Library;
import example.model.entity.User;
import example.model.entity.Video;

public class FindRelation {
    public static void main(String[] args) {
        User user= UserDAO.findByEmail("pedro@gmail.com");
        Library library= LibraryDAO.findByOwner(user);
        Video video= VideoDAO.findById(1);
        System.out.println(LibraryVideoDAO.findById(library.getId(), video.getId()));
    }
}
