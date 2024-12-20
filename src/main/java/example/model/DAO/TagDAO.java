package example.model.DAO;

import example.model.connection.database.ConnectionMySQL;
import example.model.entity.Tag;
import example.model.entity.User;
import example.model.entity.Video;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TagDAO extends Tag {
    private final static String INSERT = "INSERT INTO tag (name) VALUES (?)";
    private final static String DELETE = "DELETE FROM tag WHERE name LIKE ?";
    private final static String FINDBYID = "SELECT id,name FROM tag WHERE id=?";
    private final static String FINDBYNAME = "SELECT id,name FROM tag WHERE name LIKE ?";
    private final static String FINDVIDEOSFORTAG = "SELECT v.nombre FROM tag as t JOIN tagvideo AS tv ON tv.id_tag=t.id JOIN video as v ON v.id=tv.id_video WHERE t.name LIKE ?";
    private final static String FINDALLTAGS = "SELECT id,name FROM tag";


    public TagDAO() {
    }

    public TagDAO(int id, String name, List<Video> videos) {
        super(id, name, videos);
    }

    public TagDAO(Tag tag) {
        super(tag.getId(), tag.getName(), tag.getVideos());
    }

    public boolean insert() {
        if (this.getName() != null && !this.getName().isEmpty()) {
            Tag userToFind = findByName(this.getName());
            if (userToFind == null) {
                try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1, this.getName());
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
        }
        return false;
    }

    public boolean delete() {
        if (this.getName() != null && !this.getName().isEmpty()) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(DELETE)) {
                pst.setString(1, this.getName());
                pst.executeUpdate();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public static Tag findById(int idToFind) {
        Tag result = null;
        if (idToFind > 0) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1, idToFind);
                ResultSet rs = pst.executeQuery();
                result = new Tag();
                if (rs.next()) {
                    result.setId(rs.getInt("id"));
                    result.setName(rs.getString("name"));
                    //Conseguir videos
                }
                if (result.getId() < 1) {
                    return null;
                }
                return result;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    public static Tag findByName(String name) {
        Tag result = null;
        if (name != null && !name.isEmpty()) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDBYNAME)) {
                pst.setString(1, name);
                ResultSet rs = pst.executeQuery();
                result = new Tag();
                if (rs.next()) {
                    result.setId(rs.getInt("id"));
                    result.setName(rs.getString("name"));
                }
                if (result.getId() < 1) {
                    return null;
                }
                return result;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    public List<Video> allVideosForThisTag() {
        List<Video> videos = new ArrayList<>();
        if (this.getVideos() == null) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDVIDEOSFORTAG)) {
                pst.setString(1, this.getName());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Video tmpVideo = VideoDAO.findByName(rs.getString("v.nombre"));
                    videos.add(tmpVideo);
                }
                if (videos.get(0) == null) {
                    return null;
                }
            } catch (SQLException e) {
                return null;
            }
            this.setVideos(videos);
            return videos;
        }
        return null;
    }

    public static List<Tag> findAll() {
        List<Tag> allTags = new ArrayList<>();
        try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDALLTAGS)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Tag tmpTag = new Tag();
                    tmpTag.setId(rs.getInt("id"));
                    tmpTag.setName(rs.getString("name"));
                    allTags.add(tmpTag);
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return allTags;
    }

}
