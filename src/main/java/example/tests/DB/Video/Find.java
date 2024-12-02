package example.tests.DB.Video;

import example.model.DAO.VideoDAO;

public class Find {
    public static void main(String[] args) {
        System.out.println(VideoDAO.findById(1));
    }
}
