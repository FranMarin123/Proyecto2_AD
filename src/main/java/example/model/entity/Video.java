package example.model.entity;

import java.util.List;
import java.util.Objects;

public class Video {
    private int id;
    private String name;
    private String url;
    private User videoOwner;
    private LibraryVideo libraryVideo;
    private List<Tag> tags;

    public Video(){
        this(-1,"","",null,null,null);
    }

    public Video(int id, String name, String url, User videoOwner, LibraryVideo libraryVideo,List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.videoOwner = videoOwner;
        this.libraryVideo=libraryVideo;
        this.tags=tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getVideoOwner() {
        return videoOwner;
    }

    public void setVideoOwner(User videoOwner) {
        this.videoOwner = videoOwner;
    }

    public LibraryVideo getLibraryVideo() {
        return libraryVideo;
    }

    public void setLibraryVideo(LibraryVideo libraryVideo) {
        this.libraryVideo = libraryVideo;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Video video = (Video) object;
        return id == video.id && Objects.equals(name, video.name) && Objects.equals(url, video.url) && Objects.equals(videoOwner, video.videoOwner) && Objects.equals(libraryVideo, video.libraryVideo) && Objects.equals(tags, video.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, videoOwner, libraryVideo, tags);
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", videoOwner=" + videoOwner +
                ", libraryVideo=" + libraryVideo +
                '}';
    }
}
