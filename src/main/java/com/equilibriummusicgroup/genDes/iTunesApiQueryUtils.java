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


    public JsonObject getJson(String link) throws CustomException {
        String response = "";
        try {
            URL url = new URL(link);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            int responseCode = httpCon.getResponseCode();

//            responseCode = 201;

            System.out.println("RESPONSE CODE: " + responseCode);

            if(responseCode != 200){

                throw new CustomException("Response code is not 200");

            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpCon.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();

        } catch (MalformedURLException ex) {
            System.out.println("MalformedURLException!");
            throw new CustomException("MalformedURLException!");


        } catch (ProtocolException ex) {
            ex.printStackTrace();
            System.out.println("ProtocolException!");
            throw new CustomException("ProtocolException!");

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException!");
            throw new CustomException("IOException!");

        }

        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

        System.out.println("JSON: " + jsonObject.toString());

        return jsonObject;

    }

/*
    static class CustomException extends Exception
    {
        public CustomException(String message)
        {
            super(message);
        }
    }
*/

}