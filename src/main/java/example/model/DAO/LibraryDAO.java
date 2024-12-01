package example.model.DAO;

import example.model.connection.database.ConnectionMySQL;
import example.model.entity.Library;
import example.model.entity.LibraryVideo;
import example.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class LibraryDAO extends Library {
    private final static String INSERT = "INSERT INTO library (id_user) VALUES (?)";
    private final static String DELETE = "DELETE FROM library WHERE id_user=?";
    private final static String FINDBYID = "SELECT id,id_user FROM library WHERE id=?";
    private final static String FINDBYOWNER = "SELECT id,id_user FROM library WHERE id_user LIKE ?";


    public LibraryDAO() {
    }

    public LibraryDAO(int id, User owner, List<LibraryVideo> libraryVideo) {
        super(id, owner, libraryVideo);
    }

    public LibraryDAO(Library library) {
        super(library.getId(), library.getOwner(), library.getLibraryVideo());
    }

    public boolean save() {
        if (this.getOwner() == null || this.getId() < 0) {
            return false;
        } else {
            Library libraryToFind = findByOwner(this.getOwner());
            if (libraryToFind == null) {
                try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setInt(1, this.getOwner().getId());
                    pst.executeUpdate();
                    ResultSet rs = pst.getGeneratedKeys();
                    if (rs.first()) {
                        this.setId(rs.getInt(1));
                    }
                    this.setLibraryVideo(LibraryVideoDAO.findAllVideosForLibrary(this));
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean delete() {
        if (this.getOwner() != null && this.getId() > 0) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, this.getOwner().getId());
                pst.executeUpdate();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public static Library findById(int idToFind) {
        Library result = null;
        if (idToFind > 0) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1, idToFind);
                ResultSet rs = pst.executeQuery();
                result = new Library();
                if (rs.next()) {
                    result.setId(rs.getInt("id"));
                    result.setOwner(UserDAO.findById(rs.getInt("id_user")));
                    result.setLibraryVideo(LibraryVideoDAO.findAllVideosForLibrary(result));
                }
                return result;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    public static Library findByOwner(User owner) {
        Library result = null;
        if (owner != null && owner.getId() > 0) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDBYOWNER)) {
                pst.setInt(1, owner.getId());
                ResultSet rs = pst.executeQuery();
                result = new Library();
                if (rs.next()) {
                    result.setId(rs.getInt("id"));
                    result.setOwner(UserDAO.findById(rs.getInt("id_user")));
                    result.setLibraryVideo(LibraryVideoDAO.findAllVideosForLibrary(result));
                }
                return result;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }


}
