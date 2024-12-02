package example.controllers;

import example.App;
import example.controllers.Controller;
import example.model.DAO.LibraryDAO;
import example.model.DAO.UserDAO;
import example.model.Singleton.ActualLibrary;
import example.model.Singleton.UserSigned;
import example.model.entity.Library;
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

public class RegisterController extends Controller implements Initializable {
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField name;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void registerClick() throws IOException {
        if (name.getText() == null || name.getText().isEmpty() || email.getText() == null || email.getText().isEmpty() || password.getText() == null || password.getText().isEmpty()) {
            JavaFXUtils.showErrorAlert("ERROR: CAMPOS", "Por favor, rellene el campo");
        } else {
            User userDB = UserDAO.findByEmail(email.getText());
            if (userDB == null) {
                User tmpUser = new User(name.getText(), password.getText(), email.getText());
                UserDAO saveUser=new UserDAO(tmpUser);
                saveUser.save();
                UserSigned.getInstance(saveUser);
                LibraryDAO libraryToSave=new LibraryDAO(new Library(tmpUser));
                libraryToSave.save();
                ActualLibrary.getInstance();
                JavaFXUtils.showConfirmAlert("INICIO CORRECTO", "Te has registrado correctamente");
                App.currentController.changeScene(Scenes.MAINPAGE, null);
            } else {
                JavaFXUtils.showErrorAlert("ERROR: USUARIO", "Usuario ya existente");
            }
        }
    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.PRINCIPAL, null);
    }
}
