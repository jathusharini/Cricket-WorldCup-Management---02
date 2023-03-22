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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.Player;
import sample.models.Team;
import sample.models.Tournament;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeamProfileController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Team team;
    private List<Player> players;

    @FXML
    Label teamName;

    @FXML
    VBox playerContainer;

    public void gotoTeams(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("teams.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void displayName(String name) {
        teamName.setText(name);
        List<Team> teams = Tournament.getTeams();
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getCountry().equals(teamName.getText())) {
                this.team = teams.get(i);
                break;
            }
        }

        players = this.team.getPlayers();

        for (int i = 0; i < players.size(); i++) {
            HBox hBox = new HBox();
            Button btn = new Button(players.get(i).getName());
            int finalI = i;
            btn.setOnAction(event -> {
                final Stage stage;
                final Scene scene;
                final Parent root;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("playerProfile.fxml"));
                    root = loader.load();
                    PlayerProfileController playerProfileController = loader.getController();
                    playerProfileController.setTeamAndPlayer(team.getCountry(), players.get(finalI));
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            btn.setPrefWidth(200);
            hBox.getChildren().add(btn);
            hBox.getChildren().add(new Label(players.get(i).getRole()));
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setSpacing(10);
            playerContainer.getChildren().add(hBox);
        }
        playerContainer.setSpacing(10);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
