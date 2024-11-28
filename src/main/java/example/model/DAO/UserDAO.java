package example.model.DAO;

import example.model.connection.database.ConnectionMySQL;
import example.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO extends User{
    private final static String INSERT="INSERT INTO user (username,password,email) VALUES (?,?,?)";
    private final static String UPDATE = "UPDATE user SET REPLACE=? WHERE id=?";
    private final static String DELETE="DELETE FROM user WHERE email=?";
    private final static String FINDBYID="SELECT id,username,password,email FROM user WHERE id=?";
    private final static String FINDBYEMAIL="SELECT id,username,password,email FROM user WHERE email LIKE ?";

    public UserDAO() {
    }

    public UserDAO(String username, String password, String email) {
        this.username=username;
        this.setPassword(password);
        this.email=email;
    }

    public UserDAO(User user){
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.email=user.getEmail();
    }

    public boolean save() {
        boolean result=false;
        if (this.getEmail() == null || this.getEmail().isEmpty()) {
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
            }else {
                if (this.getUsername()!=null && !userToFind.getUsername().equals(this.getUsername())){
                    try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", "username"))) {
                        pst.setString(1, this.getUsername());
                        pst.setInt(2, userToFind.getId());
                        pst.executeUpdate();
                        result=true;
                    } catch (SQLException e) {
                        return false;
                    }
                }
                if (this.getPassword()!=null && !userToFind.getPassword().equals(this.getPassword())){
                    try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", "password"))) {
                        pst.setString(1, this.getPassword());
                        pst.setInt(2, userToFind.getId());
                        pst.executeUpdate();
                        result=true;
                    } catch (SQLException e) {
                        return false;
                    }
                }
            }
        }
        return result;
    }

    public boolean delete() {
        if (this.getEmail() != null) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(DELETE)) {
                pst.setString(1, this.getEmail());
                pst.executeUpdate();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public User findById(int idToFind) {
        User result = null;
        if (idToFind > 0) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1, idToFind);
                ResultSet rs = pst.executeQuery();
                result = new User();
                if (rs.next()) {
                    result.setId(rs.getInt("id"));
                    result.setEmail(rs.getString("email"));
                    result.setUsername(rs.getString("username"));
                    result.setPasswordWithoutHash(rs.getString("password"));
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

    public User findByEmail(String email){
        User result = null;
        if (email!=null && !email.isEmpty()) {
            try (PreparedStatement pst = ConnectionMySQL.getConnection().prepareStatement(FINDBYEMAIL)) {
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                result = new User();
                if (rs.next()) {
                    result.setId(rs.getInt("id"));
                    result.setEmail(rs.getString("email"));
                    result.setUsername(rs.getString("username"));
                    result.setPasswordWithoutHash(rs.getString("password"));
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
}
