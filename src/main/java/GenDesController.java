/**
 * Sample Skeleton for 'genDesGUI.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GenDesController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="upcTextField"
    private TextField upcTextField; // Value injected by FXMLLoader

    @FXML // fx:id="choiceBoxLanguage"
    private ChoiceBox<?> choiceBoxLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="generateButton"
    private Button generateButton; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionTextArea"
    private TextArea descriptionTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="resultTextArea"
    private TextArea resultTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="progressBar"
    private ProgressBar progressBar; // Value injected by FXMLLoader

    @FXML
    void aboutItemAction(ActionEvent event) {

    }

    @FXML
    void checkApiArtists(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert upcTextField != null : "fx:id=\"upcTextField\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert choiceBoxLanguage != null : "fx:id=\"choiceBoxLanguage\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert generateButton != null : "fx:id=\"generateButton\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert descriptionTextArea != null : "fx:id=\"descriptionTextArea\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert resultTextArea != null : "fx:id=\"resultTextArea\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'genDesGUI.fxml'.";

    }
}
