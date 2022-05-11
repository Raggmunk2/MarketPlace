package soa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Invocation {

    public static void main(String[] args) {
        try{
            URL url = new URL ("http://localhost:9998/marketPlace");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if(connection.getResponseCode()!=200){
                System.err.println("Some error happened");
                System.exit(0);
            }

            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output = "";
            while ((output = br.readLine()) != null){
                System.out.println(output);
            }
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
