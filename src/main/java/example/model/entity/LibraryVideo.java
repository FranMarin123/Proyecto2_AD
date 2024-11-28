package example.model.entity;

import java.util.Objects;

public class LibraryVideo {
    private Library library;
    private Video video;
    private boolean viewed;

    public LibraryVideo(){
        this(null,null,false);
    }

    public LibraryVideo(Library library, Video video, boolean viewed) {
        this.library = library;
        this.video = video;
        this.viewed = viewed;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LibraryVideo that = (LibraryVideo) object;
        return viewed == that.viewed && Objects.equals(library, that.library) && Objects.equals(video, that.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(library, video, viewed);
    }

    @Override
    public String toString() {
        return "LibraryVideo{" +
                "library=" + library +
                ", video=" + video +
                ", viewed=" + viewed +
                '}';
    }
}
