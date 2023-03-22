package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.models.Tournament;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        primaryStage.setTitle("ICC Cricket World Cup 2022");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
        Tournament.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
