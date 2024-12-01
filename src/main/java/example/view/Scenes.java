package example.view;

public enum Scenes {
    BASE("base.fxml"),
    PRINCIPAL("principal.fxml"),
    LOGIN("login.fxml"),
    REGISTER("register.fxml");

    private String path;

    Scenes(String path){
        this.path=path;
    }

    public String getPath(){
        return path;
    }
}
