package soa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/marketPlace")
public class OrderService {
    private ConnectionToDatabaseRepository connection;

    public OrderService() throws SQLException {
        connection = new ConnectionToDatabaseRepository();
    }

    @GET
    @Path("/orderHistory")
    @Produces({MediaType.APPLICATION_JSON})

    public String getOrderHistory() throws SQLException {
        ArrayList products = connection.getOrderHistory();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(products);
        return jsonObject;

    }
    
}
