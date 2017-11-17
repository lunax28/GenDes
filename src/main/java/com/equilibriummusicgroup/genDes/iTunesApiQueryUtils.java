package com.equilibriummusicgroup.genDes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author albus
 */
public class iTunesApiQueryUtils {


    public iTunesApiQueryUtils() {
    }


    public JsonObject getJson(String link){
        String response = "";
        try {
            URL url = new URL(link);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            int responseCode = httpCon.getResponseCode();

            System.out.println("RESPONSE CODE: " + responseCode);

            if(responseCode == 403){


                return null;

            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpCon.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();

        } catch (MalformedURLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("Exception");
            alert.setContentText("Problem with the API URL refer to the exception dialog below:");

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
            //System.out.println("MalformedURLException!");

        } catch (ProtocolException ex) {
            System.out.println("ProtocolException!");

        } catch (IOException ex) {
            System.out.println("IOException!");

        }

        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

        System.out.println("JSON: " + jsonObject.toString());

        return jsonObject;

    }

}