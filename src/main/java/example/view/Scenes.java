package example.view;

public enum Scenes {
    BASE("base.fxml"),
    PRINCIPAL("principal.fxml"),
    LOGIN("login.fxml"),
    REGISTER("register.fxml"),
    MAINPAGE("afterLogin.fxml"),
    UPLOADVIDEO("uploadVideoScreen.fxml"),
    SELECTTAG("selectTag.fxml"),
    TAGVIDEOS("videosForTag.fxml");

    private String path;

    Scenes(String path){
        this.path=path;
    }

    public String getPath(){
        return path;
    }
}
