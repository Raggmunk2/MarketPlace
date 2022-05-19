package soa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import dataAccessLayer.repositories.SoaRepositoryConnector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;

@Path("/marketPlace")
public class OrderService {
    private SoaRepositoryConnector connection;


    public OrderService() {
        connection = new SoaRepositoryConnector();

    }

    /**
     * Gets the order history and through a connection to the DB
     * @param username the user to search the order for
     * @return Json object in a String format
     */
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
