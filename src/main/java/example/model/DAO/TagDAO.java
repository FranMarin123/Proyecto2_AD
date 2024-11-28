package example.model.DAO;

import example.model.entity.Tag;
import example.model.entity.Video;

import java.util.List;

public class TagDAO extends Tag {


    public TagDAO() {
    }

    public TagDAO(String name, List<Video> videos) {
        super(name, videos);
    }

    public TagDAO(Tag tag) {
        super(tag.getName(), tag.getVideos());
    }



}
