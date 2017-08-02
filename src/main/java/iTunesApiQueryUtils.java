import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            System.out.println("MalformedURLException!");

        } catch (ProtocolException ex) {
            System.out.println("ProtocolException!");

        } catch (IOException ex) {
            System.out.println("IOException!");

        }

        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

        System.out.println("JSON: " + jsonObject.toString());

        return jsonObject;

    }


    private class MyOwnException extends Exception {

        public MyOwnException(String msg) {
            super(msg);
        }
    }





}