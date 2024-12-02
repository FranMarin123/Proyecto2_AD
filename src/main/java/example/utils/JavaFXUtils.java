package example.utils;

import example.App;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JavaFXUtils {

    public static void showErrorAlert(String title, String textAboutAlert){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(title);
        if (errorAlert.getDialogPane().getScene().getWindow()!=null) {
            Stage alertStage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(App.class.getResourceAsStream("img/iVideoSoloLogoSF.PNG")));
        }
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(textAboutAlert);
        errorAlert.showAndWait();
    }

    public static void showConfirmAlert(String title, String textAboutAlert){
        Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
        errorAlert.setTitle(title);
        if (errorAlert.getDialogPane().getScene().getWindow()!=null) {
            Stage alertStage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(App.class.getResourceAsStream("img/iVideoSoloLogoSF.PNG")));
        }
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(textAboutAlert);
        errorAlert.showAndWait();
    }


}