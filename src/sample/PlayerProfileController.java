package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sample.models.Player;
import sample.models.Team;

import java.io.IOException;

public class PlayerProfileController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private String teamName;
    private Player player;

    @FXML
    private Label playerName;

    @FXML
    private TextField txtName;

    @FXML
    private ToggleGroup role;


    public void onGoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("teamProfile.fxml"));
        root = loader.load();
        TeamProfileController teamProfileController = loader.getController();
        teamProfileController.displayName(teamName);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onSave(ActionEvent event) {
        this.player.setName(txtName.getText());
        playerName.setText(player.getName());
        txtName.setText(player.getName());

        RadioButton selectedRadioButton = (RadioButton) role.getSelectedToggle();
        player.setRole(selectedRadioButton.getText());
    }

    public void setTeamAndPlayer(String country, Player player) {
        teamName = country;
        this.player = player;

        playerName.setText(player.getName());
        txtName.setText(player.getName());
        role.getToggles().forEach(toggle -> {
            if (((RadioButton)toggle).getText().equals(player.getRole())) {
                ((RadioButton)toggle).setSelected(true);
            }
        });
    }
}
