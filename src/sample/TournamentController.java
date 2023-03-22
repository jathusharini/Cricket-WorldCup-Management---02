package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.Match;
import sample.models.MatchSummary;
import sample.models.Team;
import sample.models.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TournamentController implements Initializable {

    List<Match> matches;
    List<MatchSummary> matchSummaries = new ArrayList<>();

    // Status 0 -> no matches played, 1 -> group stages played, 2 -> playoffs played, 3 -> final played
    private int status = 0;

    @FXML
    Button btnPlay;

    @FXML
    TableView summaryTable;

    @FXML
    Label lblWinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        matches = Tournament.generateMatches();
    }

    public void onPlayTournament(ActionEvent event) throws IOException {
        if (status == 0) {
            // start group rounds
            Tournament.playMatches();

            // display match summary
            matches = Tournament.getMatches();
            for (int i = 0; i < matches.size(); i++) {
                summaryTable.getItems().add(new MatchSummary(matches.get(i)));
            }

            // generate play off matches
            Tournament.generatePlayOffs();
            btnPlay.setText("Start Play Offs");
            status = 1;
        } else if (status == 1) {
            // start play offs
            Tournament.playPlayOffs();

            for (int i = matches.size() - 2; i < matches.size(); i++) {
                summaryTable.getItems().add(new MatchSummary(matches.get(i)));
            }

            btnPlay.setText("Start Final");
            Tournament.generateFinal();
            status = 2;
        } else if (status == 2) {
            // start final
            Tournament.playFinal();

            summaryTable.getItems().add(new MatchSummary(matches.get(matches.size() - 1)));
            btnPlay.setText("View Summaries");
            lblWinner.setText("Congratulations " + matches.get(matches.size() - 1).getWinningTeam().toString());
            status = 3;
        } else if (status == 3) {
            // goto summary screen
            Parent root = FXMLLoader.load(getClass().getResource("summary.fxml"));
            Stage stage =( Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }
}
