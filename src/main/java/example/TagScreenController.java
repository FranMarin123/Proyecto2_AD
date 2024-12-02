package example;

import example.model.DAO.TagDAO;
import example.model.Singleton.ActualLibrary;
import example.model.Singleton.SelectedTag;
import example.model.Singleton.UserSigned;
import example.model.connection.FTP.ConnectionFTP;
import example.model.entity.Video;
import example.utils.JavaFXUtils;
import example.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TagScreenController extends Controller implements Initializable {
    @FXML
    private MediaView mediaView;

    private static final File tmpFile=new File("actualVideo.mp4");

    private int nVideo=0;

    private List<Video> allVideos;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TagDAO tagDAO=new TagDAO(SelectedTag.getInstance().getSelectedTag());
        allVideos=tagDAO.allVideosForThisTag();
        if (allVideos!=null && allVideos.get(0)!=null) {
            ConnectionFTP.getConnection().downloadFile(allVideos.get(nVideo).getUrl(), tmpFile.getPath());
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(tmpFile.toURI().toString()));
            mediaView.setMediaPlayer(mediaPlayer);
            mediaView.setFitHeight(450);
            mediaView.setFitWidth(500);
            mediaPlayer.play();
        }else {
            JavaFXUtils.showErrorAlert("ERROR: VIDEOS","No hay videos para este tag");
        }
    }

    public void upArrow(){
        if (nVideo==0){
            JavaFXUtils.showErrorAlert("NO HAY MAS VIDEOS","No hay mas videos hacia arriba");
        }else {
            nVideo--;
            ConnectionFTP.getConnection().downloadFile(allVideos.get(nVideo).getUrl(), tmpFile.getPath());
            MediaPlayer mediaPlayer=new MediaPlayer(new Media(tmpFile.toURI().toString()));
            mediaView.setMediaPlayer(mediaPlayer);
            mediaView.setFitHeight(450);
            mediaView.setFitWidth(500);
            mediaPlayer.play();
        }
    }

    public void downArrow(){
        if (allVideos.size()-1<=nVideo){
            JavaFXUtils.showErrorAlert("NO HAY MAS VIDEOS","No hay mas videos hacia abajo");
        }else {
            nVideo++;
            ConnectionFTP.getConnection().downloadFile(allVideos.get(nVideo).getUrl(), tmpFile.getPath());
            MediaPlayer mediaPlayer=new MediaPlayer(new Media(tmpFile.toURI().toString()));
            mediaView.setMediaPlayer(mediaPlayer);
            mediaView.setFitHeight(450);
            mediaView.setFitWidth(500);
            mediaPlayer.play();
        }
    }

    public void backClick() throws IOException {
        SelectedTag.close();
        App.currentController.changeScene(Scenes.MAINPAGE, null);
    }

}
