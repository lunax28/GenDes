/**
 * Sample Skeleton for 'genDesGUI.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.equilibriummusicgroup.genDes.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GenDesController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="upcTextField"
    private TextField upcTextField; // Value injected by FXMLLoader

    @FXML
    private ChoiceBox<String> choiceBoxLanguage;
    ObservableList<String> obsList = FXCollections.observableArrayList("EN", "ES", "DE", "IT", "PT");

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
    void doGenerateDescription(ActionEvent event) {

        if (this.upcTextField.getText().isEmpty() || this.descriptionTextArea.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Please, make sure you inserted a valid UPC and a video description");
            alert.showAndWait();

            return;
        }

        Model model = new Model(this.choiceBoxLanguage.getValue(), this.descriptionTextArea.getText());

        String tmp = upcTextField.getText();
        String tmpFormat = tmp.trim();
        String link = ("https://itunes.apple.com/lookup?upc=" + tmpFormat);
        System.out.println("LINK: " + link);


        String finalDescription = model.getResult(link);
        this.resultTextArea.setText(finalDescription);


    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert upcTextField != null : "fx:id=\"upcTextField\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert choiceBoxLanguage != null : "fx:id=\"choiceBoxLanguage\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert generateButton != null : "fx:id=\"generateButton\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert descriptionTextArea != null : "fx:id=\"descriptionTextArea\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert resultTextArea != null : "fx:id=\"resultTextArea\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        this.choiceBoxLanguage.setValue("EN");
        this.choiceBoxLanguage.setItems(this.obsList);

    }

    public void aboutItemAction() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GenDes v1.3");
        alert.setHeaderText("GenDes v1.3\n");
        alert.setContentText("Changelog:\n" +
                "Implementing concurrency");

        alert.showAndWait();


    }

    @FXML
    public void clearTextArea() {

        this.upcTextField.clear();


    }
}
