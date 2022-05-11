package soa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/marketPlace")
public class ProductService {

    private soa.ConnectionToDatabaseRepository connection;

    public ProductService() throws SQLException {
        connection = new ConnectionToDatabaseRepository();
    }

    @GET
    @Path("/allProducts")
    @Produces({MediaType.APPLICATION_JSON})

    public String getAllProducts() throws SQLException {
       ArrayList products = connection.getAllProducts();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(products);
        return jsonObject;

    }

    @GET
    @Path("/productsByCondition")
    @Produces({MediaType.APPLICATION_JSON})

    public String getProductsByCondition(@QueryParam("condition") String condition){ // ska detta bytas till type/enum?
        ArrayList products = new ArrayList();
        JSONObject product1 = new JSONObject();


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(products);
        return jsonObject;
    }

    @GET
    @Path("/productsByCondition")
    @Produces({MediaType.APPLICATION_JSON})

    public String getProductsByPricerange(@QueryParam("min") double minPrice, @QueryParam("max") double maxPrice){
        ArrayList products = new ArrayList();
        JSONObject product1 = new JSONObject();


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(products);
        return jsonObject;
    }

    @GET
    @Path("/productsByType")
    @Produces({MediaType.APPLICATION_JSON})

    public String getProductsByType(@QueryParam("type") String type){ // ska vi använda oss av enum type?
        ArrayList products = new ArrayList();
        JSONObject product1 = new JSONObject();


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(products);
        return jsonObject;
    }


    @POST
    @Path("/addProduct")
    @Produces({MediaType.APPLICATION_JSON})

    public String addProduct(@QueryParam("productName") String productName, @QueryParam("seller") String seller, @QueryParam("productType") int productType, @QueryParam("price") double price, @QueryParam("yearOfMaking") int yearOfMaking, @QueryParam("color") String color, @QueryParam("condition") int condition, @QueryParam("status") String status ){
        ArrayList products = new ArrayList();
        JSONObject product1 = new JSONObject();


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(products);
        return jsonObject;
    }











}
