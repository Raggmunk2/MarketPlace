package dataAccessLayer;
import buisnessLogicLayer.*;

import java.sql.*;
import java.util.ArrayList;


public class ProductRepository {
    private Connection connection;
    private Statement statement;
    private ArrayList<Product> allProducts;

    public ProductRepository() throws SQLException{
        ConnectionToDB connectionToDB = new ConnectionToDB();
        connection = connectionToDB.getConnection();
        statement = connection.createStatement();
    }

    /**
     * @Author : Linn Borgström
     * to get all product from the DB
     * @return an arraylist with the products
     * @throws SQLException
     */
    public ArrayList<Product> getAllProductsFromDB() throws SQLException {
        allProducts = new ArrayList<>();
        String query = "select name, seller, productTypeName, price,YearOfMaking, colour, productConditionName, [status] from Product\n" +
                "RIGHT JOIN ProductCondition ON Product.condition = ProductCondition.productConditionId\n" +
                "LEFT JOIN ProductTypes ON Product.typeOfProduct = ProductTypes.ProductTypeId\n" +
                "where  Product.[status] = 'Available';";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            String name = rs.getString(1);
            String seller = rs.getString(2);
            TypeOfProduct productTypeName = TypeOfProduct.valueOf(rs.getString(3));
            double price = rs.getDouble(4);
            int yearOfMaking = rs.getInt(5);
            Colour colour = Colour.valueOf(rs.getString(6));
            Condition productConditionName = Condition.valueOf(rs.getString(7));
            Status status = Status.valueOf(rs.getString(8));
            Product product = new Product(name,seller,productTypeName, price, yearOfMaking,colour, productConditionName, status);
            allProducts.add(product);
        }
        for (Product p:allProducts) {
            System.out.println(p.toString());

        }
        return allProducts;
    }

    /**
     * @Author : Linn Borgström
     * Makes a quarie to th DB to get a product by a price range
     * @param min minimum price double
     * @param max maximum price double
     * @return an array of products
     * @throws SQLException
     */
    public ArrayList<Product> getProductsByPriceRange(double min, double max) throws SQLException {
        ArrayList<Product> productByPrice = new ArrayList<>();
        //String query = "EXEC SP_productsByPriceSpan @minPrice = "+min +", @maxPrice = "+max +";";
        String query = "select name, seller, productTypeName, price,YearOfMaking, colour, productConditionName, [status] from Product\n" +
                "RIGHT JOIN ProductCondition ON Product.condition = ProductCondition.productConditionId\n" +
                "LEFT JOIN ProductTypes ON Product.typeOfProduct = ProductTypes.ProductTypeId\n" +
                "where  Product.[status] = 'Available' AND Product.price BETWEEN "+ min + " AND " + max +";";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            String name = rs.getString(1);
            String seller = rs.getString(2);
            TypeOfProduct productTypeName = EnumHandler.getType(rs.getString(3));
            double price = rs.getDouble(4);
            int yearOfMaking = rs.getInt(5);
            Colour colour = Colour.valueOf(rs.getString(6));
            Condition productConditionName = EnumHandler.getCondition(rs.getString(7));
            Status status = Status.valueOf(rs.getString(8));
            Product product = new Product(name,seller,productTypeName,price,yearOfMaking,colour,productConditionName,status);
            productByPrice.add(product);
        }
        for (Product p:productByPrice) {
            System.out.println(p.toString());

        }
        return productByPrice;
    }

    /**
     * @Author : Linn Borgström
     * Makes a quarie to th DB to get a product by it's condition
     * @param condition what condition the product is in (an enum)
     * @return an arrayList with the products
     * @throws SQLException
     */
    public ArrayList<Product> getProductsByCondition(Condition condition) throws SQLException {
        ArrayList<Product> productByCondition = new ArrayList<>();

        String query = "select name, seller, productTypeName, price,YearOfMaking, colour, productConditionName, [status] from Product\n" +
                "RIGHT JOIN ProductCondition ON Product.condition = ProductCondition.productConditionId\n" +
                "LEFT JOIN ProductTypes ON Product.typeOfProduct = ProductTypes.ProductTypeId\n" +
                "where  Product.[status] = 'Available' AND ProductCondition.productConditionName =  '" + condition.toString() + "';";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            String name = rs.getString(1);
            String seller = rs.getString(2);
            TypeOfProduct productTypeName = EnumHandler.getType(rs.getString(3));
            double price = rs.getDouble(4);
            int yearOfMaking = rs.getInt(5);
            Colour colour = Colour.valueOf(rs.getString(6));
            Condition productConditionName = EnumHandler.getCondition(rs.getString(7));
            Status status = Status.valueOf(rs.getString(8));
            Product product = new Product(name,seller,productTypeName,price,yearOfMaking,colour,productConditionName,status);
            productByCondition.add(product);
        }
        for (Product p:productByCondition) {
            System.out.println(p.toString());

        }
        return productByCondition;
    }

    /**
     * @Author : Linn Borgström
     * Makes a quarie to the DB to get a product by it's condition
     * @param typeOfProduct the type of product in an enum
     * @return an arraylist of the products
     * @throws SQLException
     */
    public ArrayList<Product> getProductsByTypeOfProduct(TypeOfProduct typeOfProduct) throws SQLException {
        ArrayList<Product> productByType = new ArrayList<>();

        String query = "select name, seller, productTypeName, price,YearOfMaking, colour, productConditionName, [status] from Product\n" +
                "RIGHT JOIN ProductCondition ON Product.condition = ProductCondition.productConditionId\n" +
                "LEFT JOIN ProductTypes ON Product.typeOfProduct = ProductTypes.ProductTypeId\n" +
                "where  Product.[status] = 'Available' AND ProductTypes.productTypeName = '" + typeOfProduct.toString() + "';";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            String name = rs.getString(1);
            String seller = rs.getString(2);
            TypeOfProduct productTypeName = EnumHandler.getType(rs.getString(3));
            double price = rs.getDouble(4);
            int yearOfMaking = rs.getInt(5);
            Colour colour = Colour.valueOf(rs.getString(6));
            Condition productConditionName = EnumHandler.getCondition(rs.getString(7));
            Status status = Status.valueOf(rs.getString(8));
            Product product = new Product(name,seller,productTypeName,price,yearOfMaking,colour,productConditionName,status);
            productByType.add(product);
        }
        for (Product p:productByType) {
            System.out.println(p.toString());

        }
        return productByType;
    }




    /**
     * A user can add a new product to the DB
     * @param product the product to add
     * @return true if connection to database was successfull
     */
    public boolean addProductToDatabase(Product product) {
        try {
            Connection connect = connection;
            CallableStatement callableStatement = connect.prepareCall("{CALL MarketPlace.dbo.sp_addNewProduct(?, ?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, product.getName());
            callableStatement.setString(2, product.getSeller());
            callableStatement.setInt(3, product.getTypeOfProductName().ordinal());
            callableStatement.setInt(4, (int) Math.round(product.getPrice())); // TODO göras om till float/double som i DB? /Linn
            callableStatement.setInt(5, product.getYearOfMaking());
            callableStatement.setString(6, product.getColour().toString());
            callableStatement.setInt(7, product.getCondition().ordinal());
            callableStatement.setString(8, product.getStatus().toString());
            callableStatement.execute();

            return true;
        }catch (SQLException ex) {
            System.out.println("---> Error add product to DB: \n" + ex.getMessage() + "\n");
            return false;
        }
    }

    /**
     * User can add a product to Cart. It then becomes unavailable
     * @param username name of buyer
     * @param localTime time when adding product
     * @param productId id of product to add
     * @return true if connection to database was successfull
     */
    public boolean addProductToCart(String username, Timestamp localTime, int productId){
        try {
            Connection connect = connection;
            CallableStatement callableStatement = connect.prepareCall("{CALL MarketPlace.dbo.sp_addProductToOrder(?, ?, ?)}");
            callableStatement.setString(1, username);
            callableStatement.setTimestamp(2, localTime);
            callableStatement.setInt(3, productId);
            callableStatement.execute();

            return true;
        }catch (SQLException ex){
            System.out.println("---> Error add product to Order: \n" + ex.getMessage() + "\n");
            return false;
        }
    }
    public ArrayList getAllUnavailableProducts(String username) throws SQLException {
        ArrayList<Product> unavailableProducts = new ArrayList<>();

        String query = "select productId,[name], seller, productTypeName, price,YearOfMaking, colour, productConditionName, [status] from Product\n" +
                "RIGHT JOIN ProductCondition ON Product.condition = ProductCondition.productConditionId\n" +
                "LEFT JOIN ProductTypes ON Product.typeOfProduct = ProductTypes.ProductTypeId\n" +
                "where  Product.[status] = 'Unavailable' AND seller = '" + username +"'; " ;
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            int productId = rs.getInt(1);
            String productName = rs.getString(2);
            String seller = rs.getString(3);
            TypeOfProduct productTypeName = TypeOfProduct.valueOf(rs.getString(4));
            double price = rs.getDouble(5);
            int yearOfMaking = rs.getInt(6);
            Colour colour = Colour.valueOf(rs.getString(7));
            Condition productConditionName = Condition.valueOf(rs.getString(8));
            Status status = Status.valueOf(rs.getString(9));
            Product product = new Product(productId,productName,seller,productTypeName, price, yearOfMaking,colour, productConditionName, status);
            unavailableProducts.add(product);
        }
        for (Product p:unavailableProducts) {
            System.out.println(p.toString());
            unavailableProducts.add(p);
        }
        return unavailableProducts;
    }

}
