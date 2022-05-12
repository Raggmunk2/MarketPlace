package soa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author: Annie Tran, Linn Borgström
 * Class to get a users order history
 */

@Path("/marketPlace")
public class OrderService {
    private ConnectionToDatabaseRepository connection;


    public OrderService() throws SQLException {
        connection = new ConnectionToDatabaseRepository();
    }

    /**
     * Gets the order history and throught a connection to the DB
     * @return Json object in a String format
     * @throws SQLException
     */
    @GET
    @Path("/orderHistory")
    @Produces({MediaType.APPLICATION_JSON})

    public String getOrderHistory() {
        ArrayList products = connection.getOrderHistory();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(products);
        return jsonObject;

    }
    @GET
    @Path("/orderSearch")
    @Produces({MediaType.APPLICATION_JSON})
    public String getOrderHistoryBySearch(@QueryParam("username") String username) {
        ArrayList products = connection.getOrderHistoryByName(username);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(products);
        return jsonObject;

    }
    
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
