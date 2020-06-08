package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1080;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("screen_store.fxml"));
        primaryStage.setTitle("store");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.getIcons().add(new Image("file:assets\\tau.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
