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
    private final static String INSERT="INSERT INTO library (id_user) VALUES (?)";
    private final static String DELETE="DELETE FROM user WHERE email=?";
    private final static String FINDBYID="SELECT id,username,password,email FROM user WHERE id=?";
    private final static String FINDBYEMAIL="SELECT id,username,password,email FROM user WHERE email LIKE ?";


    public LibraryDAO() {
    }

    public LibraryDAO(int id, User owner, List<LibraryVideo> libraryVideo) {
        super(id, owner, libraryVideo);
    }

    public LibraryDAO(Library library) {
        super(library.getId(), library.getOwner(), library.getLibraryVideo());
    }

    public boolean save(){
        boolean result=false;
        /*if (this.getEmail() == null || this.getEmail().isEmpty()) {
            return false;
        }else {
            User userToFind=findByEmail(this.getEmail());
            if (userToFind==null){
                try (PreparedStatement pst= ConnectionMySQL.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
                    pst.setString(1, this.getUsername());
                    pst.setString(2, this.getPassword());
                    pst.setString(3, this.getEmail());
                    pst.executeUpdate();
                    ResultSet rs= pst.getGeneratedKeys();
                    if (rs.first()){
                        this.setId(rs.getInt(1));
                    }
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }
        }*/
        return result;
    }

    public static Library findById(int idToFind){
        return null;
    }
}
