package sample;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import sample.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {
    List<Match> matches;
    List<MatchSummary> matchSummaries = new ArrayList<>();

    List<Player> players;
    List<PlayerSummary> playerSummaries = new ArrayList<>();

    List<Team> teams = Tournament.getTeams();
    List<GroupSummary> groupASummary = new ArrayList<>(); ;
    List<GroupSummary> groupBSummary = new ArrayList<>();

    @FXML
    Pane summaryContainer;

    @FXML
    TableView<MatchSummary> matchesSummary;

    @FXML
    TableView<PlayerSummary> playerSummary;

    @FXML
    TableView<GroupSummary> groupSummary;

    @FXML
    Label lblSummary;

    public void onMatchSummary(ActionEvent event) {
        lblSummary.setText("Matches Summary");

        matchesSummary.setVisible(true);
        playerSummary.setVisible(false);
        groupSummary.setVisible(false);
    }

    public void onGroupASummary(ActionEvent event) {
        lblSummary.setText("Group A Summary");

        groupSummary.getItems().clear();
        groupSummary.getItems().addAll(groupASummary);

        matchesSummary.setVisible(false);
        playerSummary.setVisible(false);
        groupSummary.setVisible(true);
    }

    public void onGroupBSummary(ActionEvent event) {
        lblSummary.setText("Group B Summary");

        groupSummary.getItems().clear();
        groupSummary.getItems().addAll(groupBSummary);

        matchesSummary.setVisible(false);
        playerSummary.setVisible(false);
        groupSummary.setVisible(true);
    }

    public void onPlayerSummary(ActionEvent event) {
        lblSummary.setText("Players Summary");

        matchesSummary.setVisible(false);
        playerSummary.setVisible(true);
        groupSummary.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // populate matches summary
        matches = Tournament.getMatches();
        for (int i = 0; i < matches.size(); i++) {
            matchSummaries.add(new MatchSummary(matches.get(i)));
        }
        matchesSummary.getItems().addAll(matchSummaries);

        // populate player summary
        for (int i = 0; i < teams.size(); i++) {
            List<Player> players = teams.get(i).getPlayers();
            for (int j = 0; j < players.size(); j++) {
                playerSummaries.add(new PlayerSummary(players.get(j), teams.get(i).getCountry()));
            }
        }
        playerSummary.getItems().addAll(playerSummaries);

        // populate summaries
        for (int i = 0; i < teams.size() / 2; i++) {
            groupASummary.add(new GroupSummary(teams.get(i)));
        }

        for (int i = teams.size() / 2; i < teams.size(); i++) {
            groupBSummary.add(new GroupSummary(teams.get(i)));
        }
    }


    public void onFinish(ActionEvent event) {
        // Write summaries to files
        writeToFile("matches.csv", matchSummaries);
        writeToFile("players.csv", playerSummaries);
        writeToFile("groupAStandings.csv", groupASummary);
        writeToFile("groupBStandings.csv", groupBSummary);

        System.exit(0);
    }

    public void writeToFile(String fileName, List<?> data) {
        try (FileWriter writer = new FileWriter("src/sample/csv/" + fileName)) {
            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            beanWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}
