package example.controllers;

import example.App;
import example.controllers.Controller;
import example.model.DAO.UserDAO;
import example.model.Singleton.ActualLibrary;
import example.model.Singleton.UserSigned;
import example.model.entity.User;
import example.utils.JavaFXUtils;
import example.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginClick() throws IOException {
        if (email.getText()==null || email.getText().isEmpty() || password.getText()==null || password.getText().isEmpty()){
            JavaFXUtils.showErrorAlert("ERROR: CAMPOS","Por favor, rellene el campo");
        }else {
            User userDB = UserDAO.findByEmail(email.getText());
            if (userDB!=null){
                User tmpUser=new User("",password.getText(), email.getText());
                if (tmpUser.getEmail().equals(userDB.getEmail()) && tmpUser.getPassword().equals(userDB.getPassword())){
                    UserSigned.getInstance(userDB);
                    ActualLibrary.getInstance();
                    JavaFXUtils.showConfirmAlert("INICIO CORRECTO","Has iniciado sesion correctamente");
                    App.currentController.changeScene(Scenes.MAINPAGE,null);
                }else {
                    JavaFXUtils.showErrorAlert("ERROR: USUARIO","Datos de inicio de sesion incorrectos");
                }
            }else {
                JavaFXUtils.showErrorAlert("ERROR: USUARIO","Este usuario no existe");
            }
        }

    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.PRINCIPAL, null);
    }
}
