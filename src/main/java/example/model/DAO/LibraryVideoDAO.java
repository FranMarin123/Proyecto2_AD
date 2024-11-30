package example.model.DAO;

import example.model.connection.database.ConnectionMySQL;
import example.model.entity.Library;
import example.model.entity.LibraryVideo;
import example.model.entity.User;
import example.model.entity.Video;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryVideoDAO {
    private final static String INSERT = "INSERT INTO videolibrary (id_video,id_library,visto) VALUES (?,?,?)";
    private final static String DELETE = "DELETE FROM videolibrary WHERE id_video=? AND id_library=?";
    private final static String FINDLIBRARYVIDEO = "SELECT FROM videolibrary WHERE id_video=? AND id_library=? ";

    public static boolean save(Library libraryToSave, Video videoToSave) {
        if (libraryToSave.getId() < 0 || videoToSave.getId() < 0) {
            return false;
        }
        LibraryVideo libraryVideoToFind = findById(libraryToSave.getId(), videoToSave.getId());
        Library libraryToFind = LibraryDAO.findById(libraryToSave.getId());
        Video videoToFind = VideoDAO.findById(videoToSave.getId());

        if (libraryVideoToFind == null && (videoToFind != null && libraryToFind != null)) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1, videoToFind.getId());
                pst.setInt(2, libraryToFind.getId());
                pst.setBoolean(3, false);
                pst.executeUpdate();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean delete(Library libraryToDelete, Video videoToDelete) {
        if (libraryToDelete.getId() < 0 || videoToDelete.getId() < 0) {
            return false;
        }
        LibraryVideo libraryVideoToFind = findById(libraryToDelete.getId(), videoToDelete.getId());
        try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(DELETE)) {
            pst.setInt(1, videoToDelete.getId());
            pst.setInt(2, libraryToDelete.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static LibraryVideo findById(int idLibraryToFind, int idVideoToFind) {
        LibraryVideo result = null;
        if (idLibraryToFind > 0 && idVideoToFind > 0) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDLIBRARYVIDEO)) {
                pst.setInt(1, idVideoToFind);
                pst.setInt(2, idLibraryToFind);
                ResultSet rs = pst.executeQuery();
                result = new LibraryVideo();
                if (rs.next()) {
                    result.setLibrary(LibraryDAO.findById(rs.getInt("id_video")));
                    result.setVideo(VideoDAO.findById(rs.getInt("id_library")));
                    result.setViewed(rs.getBoolean("visto"));
                }
                if (result.getLibrary() == null || result.getVideo() == null) {
                    return null;
                }
                return result;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

}
