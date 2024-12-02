package example.model.DAO;

import example.model.connection.database.ConnectionMySQL;
import example.model.entity.Tag;
import example.model.entity.Video;
import example.model.entity.User;
import example.model.entity.LibraryVideo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO extends Video {
    private static final String INSERT = "INSERT INTO video (nombre, url, id_user) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE video SET REPLACE = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM video WHERE id = ?";
    private static final String FINDBYID = "SELECT id, nombre, url, id_user FROM video WHERE id = ?";
    private static final String FINDBYNAME = "SELECT id, nombre, url, id_user FROM video WHERE nombre LIKE ?";
    private static final String FINDTAGFORVIDEOS = "SELECT tv.id_video FROM video as v JOIN tagvideo AS tv ON tv.id_video=v.id WHERE v.name LIKE ?";
    private static final String FINDALLVIDEOS="SELECT id, nombre, url, id_user FROM video ORDER BY nombre ASC";
    private static final String ADDTAGTOAVIDEO="INSERT INTO tagvideo (id_tag,id_video) VALUES (?,?)";

    public VideoDAO() {
    }

    public VideoDAO(String name, String url, User usuario, LibraryVideo libraryVideo) {
        this.name = name;
        this.url = url;
        this.videoOwner = usuario;
        this.libraryVideo = libraryVideo;
    }

    public VideoDAO(Video video) {
        this.name = video.getName();
        this.url = video.getUrl();
        this.videoOwner = video.getVideoOwner();
        this.libraryVideo = video.getLibraryVideo();
        this.tags = video.getTags();
    }

    public boolean save() {
        if (this.getName() == null || this.getName().isEmpty() || this.getUrl() == null || this.getUrl().isEmpty()) {
            return false;
        }

        Video videoToFind = findById(this.id);
        if (videoToFind == null) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, this.getName());
                pst.setString(2, this.getUrl());
                pst.setInt(3, this.getVideoOwner().getId());
                pst.executeUpdate();
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.first()) {
                    this.setId(rs.getInt(1));
                }
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public boolean delete() {
        if (this.getId() > 0) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, this.getId());
                pst.executeUpdate();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public static Video findById(int idToFind) {
        if (idToFind <= 0) {
            return null;
        }

        try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDBYID)) {
            pst.setInt(1, idToFind);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Video video = new Video();
                video.setId(rs.getInt("id"));
                video.setName(rs.getString("nombre"));
                video.setUrl(rs.getString("url"));
                video.setVideoOwner(UserDAO.findById(rs.getInt("id_user")));
                return video;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public static Video findByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDBYNAME)) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return findById(rs.getInt("id"));
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public List<Tag> allTagsForThisVideo() {
        List<Tag> tags = new ArrayList<>();
        try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDTAGFORVIDEOS)) {
            pst.setString(1, this.getName());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Tag tmpTag = TagDAO.findById(rs.getInt("id_tag"));
                tags.add(tmpTag);
            }
            if (tags.get(0) != null) {
                return null;
            }
            this.setTags(tags);
            return tags;
        } catch (SQLException e) {
            return null;
        }
    }

    public static List<Video> getVideosForThisUser(User userToExcludeVideos){
        List<Video> videosForUser=new ArrayList<>();
        if (userToExcludeVideos == null || userToExcludeVideos.getEmail().isEmpty() || userToExcludeVideos.getEmail()==null) {
            return null;
        }

        try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDALLVIDEOS)) {
            try(ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Video tmpVideo = new Video();
                    tmpVideo.setId(rs.getInt("id"));
                    tmpVideo.setName(rs.getString("nombre"));
                    tmpVideo.setUrl(rs.getString("url"));
                    tmpVideo.setVideoOwner(UserDAO.findById(rs.getInt("id_user")));
                    if (!tmpVideo.getVideoOwner().equals(userToExcludeVideos)) {
                        videosForUser.add(tmpVideo);
                    }
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return videosForUser;
    }

    public static boolean addTagToAVideo(Video video,Tag tag){
        if (video.getName()==null || video.getName().isEmpty() || tag.getName()==null || tag.getName().isEmpty()){
            return false;
        }

        Video videoToAdd=findByName(video.getName());
        Tag tagToAdd=TagDAO.findByName(tag.getName());
        try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(ADDTAGTOAVIDEO)) {
            pst.setInt(1, tagToAdd.getId());
            pst.setInt(2, video.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}


