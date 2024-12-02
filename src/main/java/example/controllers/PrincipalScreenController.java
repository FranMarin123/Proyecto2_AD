package example.controllers;

import example.App;
import example.controllers.Controller;
import example.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalScreenController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void onOpen(Object input) {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void exitClick() {
        System.exit(0);
    }

    public void loginClick() throws IOException {
        App.currentController.changeScene(Scenes.LOGIN, null);
    }

    public void registerClick() throws IOException {
        App.currentController.changeScene(Scenes.REGISTER, null);
    }


}