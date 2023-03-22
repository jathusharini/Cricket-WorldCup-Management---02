package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.models.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamsController implements Initializable {

    @FXML
    VBox groupA;

    @FXML
    VBox groupB;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] teams = Tournament.getTeamNames();



        for (int i = 0; i < teams.length; i++) {
            Button btn = new Button(teams[i]);
            btn.setPrefWidth(150);
            int finalI = i;
            btn.setOnAction(event -> {
                final Stage stage;
                final Scene scene;
                final Parent root;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("teamProfile.fxml"));
                    root = loader.load();
                    TeamProfileController teamProfileController = loader.getController();
                    teamProfileController.displayName(teams[finalI]);
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            if (i < teams.length / 2) {
                groupA.getChildren().add(btn);
            } else {
                groupB.getChildren().add(btn);
            }
        }
        groupA.setSpacing(10);
        groupA.setAlignment(Pos.CENTER);
        groupB.setSpacing(10);
        groupB.setAlignment(Pos.CENTER);
    }


    public void startTournament(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tournament.fxml"));
        Stage stage =( Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
