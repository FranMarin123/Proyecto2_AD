package example.model.entity;

import java.util.Objects;

public class Library {
    protected int id;
    protected User owner;
    protected LibraryVideo libraryVideo;

    public Library() {
        this(-1,null,null);
    }

    public Library(int id, User owner, LibraryVideo libraryVideo) {
        this.id = id;
        this.owner = owner;
        this.libraryVideo = libraryVideo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LibraryVideo getLibraryVideo() {
        return libraryVideo;
    }

    public void setLibraryVideo(LibraryVideo libraryVideo) {
        this.libraryVideo = libraryVideo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Library library = (Library) object;
        return id == library.id && Objects.equals(owner, library.owner) && Objects.equals(libraryVideo, library.libraryVideo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, libraryVideo);
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", owner=" + owner +
                ", libraryVideo=" + libraryVideo +
                '}';
    }
}
