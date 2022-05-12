package soa;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Invocation {

    public static void main(String[] args) {
        try{
            URL url = new URL("http://localhost:9998/marketPlace/orderHistory/");
            //URL url = new URL("http://localhost:9998/marketPlace/orderSearch/?username=Eric");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");

            if(httpURLConnection.getResponseCode()!= 200){

                System.err.println("Some error happend");
                System.exit(0);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String output = "";
            while((output = br.readLine()) !=null){
                sb.append(output);

            }
            br.close();
            httpURLConnection.disconnect();
            parse(sb.toString());


        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author: Linn Borgström
     * A method to parse the text to a JsonObject
     * @param responseBody the text to parse
     */
    public static void parse(String responseBody){
        JSONArray orderArray = new JSONArray(responseBody);
        for (int i = 0;i < orderArray.length(); i++) {
            JSONObject order = orderArray.getJSONObject(i);
            String buyerName = (String) order.getJSONObject("buyer").get("userName");
            String sellerName = (String) order.getJSONObject("seller").get("userName");
            String typeOfProduct = order.getString("typeOfProduct");
            double price = order.getDouble("price");
            String productName = order.getString("productName");
            String condition = order.getString("condition");
            System.out.println("Buyer: " + buyerName + " productName: " + productName+ " typeOfProduct: " +typeOfProduct+ " price: " + price+ " condition: " +condition + " seller: " + sellerName);
        }
    }


}
