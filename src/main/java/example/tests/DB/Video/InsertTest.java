package example.tests.DB.Video;

import example.model.DAO.UserDAO;
import example.model.DAO.VideoDAO;
import example.model.entity.User;
import example.model.entity.Video;

public class InsertTest {
    public static void main(String[] args) {
        User user= UserDAO.findByEmail("juan@gmail.com");
        Video video=new Video("Futbol1","/ftp/Futbol2.mp4",user);
        VideoDAO videoDAO=new VideoDAO(video);
        System.out.println(videoDAO.save());
    }
}
