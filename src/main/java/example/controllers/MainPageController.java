package example.controllers;

import example.App;
import example.controllers.Controller;
import example.model.Singleton.ActualLibrary;
import example.model.Singleton.UserSigned;
import example.model.connection.FTP.ConnectionFTP;
import example.utils.JavaFXUtils;
import example.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MainPageController extends Controller implements Initializable {
    @FXML
    private MediaView mediaView;

    private static final File tmpFile=new File("actualVideo.mp4");

    private int nVideo=0;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionFTP.getConnection().downloadFile(ActualLibrary.getInstance().getCurrentLibrary().getLibraryVideo().get(nVideo).getVideo().getUrl(), tmpFile.getPath());
        MediaPlayer mediaPlayer=new MediaPlayer(new Media(tmpFile.toURI().toString()));
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setFitHeight(450);
        mediaView.setFitWidth(500);
        mediaPlayer.play();
    }

    public void upArrow(){
        if (nVideo==0){
            JavaFXUtils.showErrorAlert("NO HAY MAS VIDEOS","No hay mas videos hacia arriba");
        }else {
            nVideo--;
            ConnectionFTP.getConnection().downloadFile(ActualLibrary.getInstance().getCurrentLibrary().getLibraryVideo().get(nVideo).getVideo().getUrl(), tmpFile.getPath());
            MediaPlayer mediaPlayer=new MediaPlayer(new Media(tmpFile.toURI().toString()));
            mediaView.setMediaPlayer(mediaPlayer);
            mediaView.setFitHeight(450);
            mediaView.setFitWidth(500);
            mediaPlayer.play();
        }
    }

    public void downArrow(){
        if (ActualLibrary.getInstance().getCurrentLibrary().getLibraryVideo().size()-1<=nVideo){
            JavaFXUtils.showErrorAlert("NO HAY MAS VIDEOS","No hay mas videos hacia abajo");
        }else {
            nVideo++;
            ConnectionFTP.getConnection().downloadFile(ActualLibrary.getInstance().getCurrentLibrary().getLibraryVideo().get(nVideo).getVideo().getUrl(), tmpFile.getPath());
            MediaPlayer mediaPlayer=new MediaPlayer(new Media(tmpFile.toURI().toString()));
            mediaView.setMediaPlayer(mediaPlayer);
            mediaView.setFitHeight(450);
            mediaView.setFitWidth(500);
            mediaPlayer.play();
        }
    }

    public void backClick() throws IOException {
        UserSigned.close();
        ActualLibrary.close();
        App.currentController.changeScene(Scenes.PRINCIPAL, null);
    }

    public void subirClick() throws IOException {
        App.currentController.changeScene(Scenes.UPLOADVIDEO, null);
    }

    public void seleccionarTagClick() throws IOException {
        App.currentController.changeScene(Scenes.SELECTTAG, null);
    }
}
