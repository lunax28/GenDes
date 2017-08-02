import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import emoji4j.EmojiUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class Controller {

    @FXML
    private TextField upcTextField;

    @FXML
    private Label sourceLabel;

    @FXML
    private File sourceFolderPath;

    private File logFile;


    @FXML
    public void locateFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        this.sourceFolderPath = fileChooser.showOpenDialog(new Stage());

        if (this.sourceFolderPath != null) {
            this.sourceLabel.setText(this.sourceFolderPath.getAbsolutePath().toString());
        }


    }

    @FXML
    public void clearTextArea(){

        this.upcTextField.clear();


    }


    @FXML
    public void checkApiArtists() {


        iTunesApiQueryUtils apiClass = new iTunesApiQueryUtils();
        String link = "";

        Scanner scanner = null;
        String tmp = "";
        String tmpFormat = "";

        BufferedWriter bw = null;
        FileWriter fw = null;

        //this.logFile = new File(this.sourceFolderPath + "/UpcChecker.txt");

        //try {


            //fw = new FileWriter(this.sourceFolderPath);
            //bw = new BufferedWriter(fw);

            tmp = upcTextField.getText();
            tmpFormat = tmp.trim();
            link = ("https://itunes.apple.com/lookup?upc=" + tmpFormat);
            System.out.println("LINK: " + link);


            JsonObject json = apiClass.getJson(link);

            if (json == null) {
                //JOptionPane.showMessageDialog(this, "RATE LIMIT!\nWait a few seconds before resuming the API CALLS\nRefer to the developer for further info", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int result = json.get("resultCount").getAsInt();

            System.out.println("APIGUI JSON: " + json.toString());
            System.out.println("RESULTCOUNT: " + result);

            if (result > 0) {

                JsonArray jArray = json.get("results").getAsJsonArray();

                JsonObject jsonObjArr = jArray.get(0).getAsJsonObject();

                String albumUrl = jsonObjArr.get("collectionViewUrl").getAsString();

                System.out.println("ALBUM URL: " + albumUrl);


                //bw.write(tmp + "," + lineSeparator());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setContentText("No iTunes Albums associated with this UPC.\nInsert a different UPC.");
                alert.showAndWait();

                return;

            }


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("DONE!");
        alert.setContentText("The results are saved in your .txt file!");

        alert.showAndWait();


    }


    public void testEmoji() {

        System.out.println(EmojiUtils.getEmoji("blue_car").getEmoji());


    }
}
