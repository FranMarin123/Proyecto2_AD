package example.tests.DB.Tag;

import example.model.DAO.TagDAO;
import example.model.entity.Tag;

public class InsertTest {
    public static void main(String[] args) {
        Tag tagToInsert=new Tag("Musica");
        TagDAO tagDAO=new TagDAO(tagToInsert);
        System.out.println(tagDAO.insert());
    }
}
