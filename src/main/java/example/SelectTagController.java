package example;

import example.model.DAO.TagDAO;
import example.model.Singleton.SelectedTag;
import example.model.entity.Tag;
import example.utils.JavaFXUtils;
import example.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectTagController extends Controller implements Initializable {

    @FXML
    private ChoiceBox<String> opcionesChoiceBox = new ChoiceBox<>();

    @Override
    public void onOpen(Object input) throws IOException {
        List<Tag> allTags = TagDAO.findAll();
        for (Tag tag : allTags) {
            opcionesChoiceBox.getItems().add(tag.getName());
        }
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void selectClick() throws IOException {
        if (opcionesChoiceBox.getValue()!=null){
            Tag tagSelected=TagDAO.findByName(opcionesChoiceBox.getValue());
            SelectedTag.getInstance(tagSelected);
            App.currentController.changeScene(Scenes.TAGVIDEOS,null);
        }else {
            JavaFXUtils.showErrorAlert("ERROR: TAG","Selecciona una tag correcta, por favor");
        }
    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.MAINPAGE, null);
    }
}
