package example.tests.DB.Tag;

import example.model.DAO.TagDAO;
import example.model.DAO.VideoDAO;
import example.model.entity.Tag;
import example.model.entity.Video;

public class FindTest {
    public static void main(String[] args) {
        Tag tag=TagDAO.findByName("Deportes");
        System.out.println(tag);
    }
}
