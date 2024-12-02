package example;

import example.model.DAO.TagDAO;
import example.model.DAO.VideoDAO;
import example.model.Singleton.UserSigned;
import example.model.connection.FTP.ConnectionFTP;
import example.model.entity.Tag;
import example.model.entity.Video;
import example.utils.JavaFXUtils;
import example.view.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UploadController extends Controller implements Initializable {
    @FXML
    private TextField nombre;

    @FXML
    private TextField ruta;

    @FXML
    private ChoiceBox<String> opcionesChoiceBox=new ChoiceBox<>();

    @FXML
    private Button rutaButton;

    @Override
    public void onOpen(Object input) throws IOException {
        List<Tag> allTags= TagDAO.findAll();
        for (Tag tag:allTags){
            opcionesChoiceBox.getItems().add(tag.getName());
        }
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void seleccionarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todos los archivos", "*.mp4")
        );

        Stage stage = (Stage) rutaButton.getScene().getWindow();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            ruta.setText(archivoSeleccionado.getAbsolutePath());
        }
    }

    public void subirClick() throws IOException {
        if (nombre==null || nombre.getText().isEmpty() || ruta==null || ruta.getText().isEmpty() || opcionesChoiceBox.getValue().isEmpty()){
            JavaFXUtils.showErrorAlert("ERROR: CAMPOS", "Introduce los campos correctamente");
        }else {
            Video tmpVideo= VideoDAO.findByName(nombre.getText());
            if (tmpVideo!=null){
                JavaFXUtils.showErrorAlert("ERROR: NOMBRE", "El video ya existe");
            }else {
                Video videoToAdd=new Video(nombre.getText(),"/ftp/"+nombre.getText()+".mp4", UserSigned.getInstance().getCurrentUser());
                Tag tagToThisVideo=TagDAO.findByName(opcionesChoiceBox.getValue());
                VideoDAO videoDAO=new VideoDAO(videoToAdd);
                videoDAO.save();
                VideoDAO.addTagToAVideo(videoDAO,tagToThisVideo);
                ConnectionFTP.getConnection().uploadFile(ruta.getText(),"/ftp/"+nombre.getText()+".mp4");
                App.currentController.changeScene(Scenes.MAINPAGE,null);
            }
        }
    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.MAINPAGE, null);
    }
}
