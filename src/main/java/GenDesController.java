/**
 * Sample Skeleton for 'genDesGUI.fxml' GenDesController Class
 */

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

import com.equilibriummusicgroup.genDes.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class GenDesController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="upcTextField"
    private TextField upcTextField; // Value injected by FXMLLoader

    @FXML
    private ChoiceBox<String> choiceBoxLanguage;

    private ObservableList<String> obsList = FXCollections.observableArrayList("EN", "ES", "DE", "IT", "PT", "FR");

    @FXML
    private ChoiceBox<String> choiceBoxChannel;

    private ObservableList<String> obsListChannel = FXCollections.observableArrayList("MRC", "Buddha", "Sleep");

    @FXML // fx:id="generateButton"
    private Button generateButton; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionTextArea"
    private TextArea descriptionTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="resultTextArea"
    private TextArea resultTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="progressBar"
    private ProgressBar progressBar; // Value injected by FXMLLoader


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert upcTextField != null : "fx:id=\"upcTextField\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert choiceBoxLanguage != null : "fx:id=\"choiceBoxLanguage\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert generateButton != null : "fx:id=\"generateButton\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert descriptionTextArea != null : "fx:id=\"descriptionTextArea\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert resultTextArea != null : "fx:id=\"resultTextArea\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'genDesGUI.fxml'.";
        this.choiceBoxLanguage.setValue("EN");
        this.choiceBoxLanguage.setItems(this.obsList);
        this.choiceBoxChannel.setValue("MRC");
        this.choiceBoxChannel.setItems(this.obsListChannel);
    }


    /**
     * Method that builds the youtube description with the album url associated with a specific UPC.
     *
     * @param event the event object passed when clicking on Generate
     */
    @FXML
    void doGenerateDescription(ActionEvent event) {

        if (this.upcTextField.getText().isEmpty() || this.descriptionTextArea.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Please, make sure you inserted a valid UPC and a video description");
            alert.showAndWait();
            return;
        }

        if (!this.upcTextField.getText().matches("[0-9]{13}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Please, check the UPC format!");
            alert.showAndWait();
            return;
        }

        String tmp = upcTextField.getText();
        String tmpFormat = tmp.trim();
        String link = ("https://itunes.apple.com/lookup?upc=" + tmpFormat);
        System.out.println("LINK: " + link);

        Model model = new Model(this.choiceBoxLanguage.getValue(),  this.choiceBoxChannel.getValue(), this.descriptionTextArea.getText());

        Task<String> task = new Task<String>() {
            @Override
            protected String call() throws Exception {
                updateProgress(-1, -1);
                String finalDescription = model.getResult(link);
                updateProgress(1, 1);
                return finalDescription;
            }
        };

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                resultTextArea.setText(task.getValue());
            }
        });

        task.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {

                Throwable ex = event.getSource().getException();

                String exceptionMessage = ex.getMessage();
                System.out.println("exceptionMessage: " + exceptionMessage);

                displayExceptionDialog(ex, exceptionMessage);
            }
        });

        this.progressBar.progressProperty().bind(task.progressProperty());

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }

    private void displayExceptionDialog(Throwable ex, String exceptionMessage) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception");
        alert.setContentText(exceptionMessage);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }


    @FXML
    public void clearTextArea() {
        this.upcTextField.clear();
    }

    @FXML
    void aboutItemAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GenDes v1.4");
        alert.setHeaderText("GenDes v1.4\n");
        alert.setContentText("Changelog:\n" +
                "Added concurrency");

        alert.showAndWait();
    }

    @FXML
    void doCopyAll(ActionEvent event) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(this.resultTextArea.getText());
        clipboard.setContent(content);
    }
}
