package dataAccessLayer.repositories;
import dataAccessLayer.ConnectionToDB;
import shared.*;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepository {
    private Connection connection;
    private Statement statement;

    public ProductRepository() {
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            connection = connectionToDB.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author : Linn Borgström
     * to get all product from the DB
     * @return an arraylist with the products
     * @throws SQLException
     */
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();
        try{
            String query = "select * from Product where [status] = 'Available';";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String seller = rs.getString(3);
                int productType = rs.getInt(4);
                double price = rs.getDouble(5);
                int yearOfMaking = rs.getInt(6);
                String colour = rs.getString(7);
                int productCondition = rs.getInt(8);
                Status status = Status.valueOf(rs.getString(9));
                Product product = new Product(id, name, seller, EnumHandler.getType(productType), price, yearOfMaking, colour, EnumHandler.getCondition(productCondition), status);
                allProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Product p:allProducts) {
            System.out.println(p.toString());
        }
        return allProducts;
    }

    /**
     * @Author : Linn Borgström
     * Makes a querie to th DB to get a product by a price range
     * @return an array of products
     * @throws SQLException
     */
    public ArrayList<Product> getProductsByPriceRange(double[] range) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String query = "select * from Product where  Product.[status] = 'Available' AND Product.price BETWEEN " + range[0] + " AND " + range[1] + ";";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String seller = rs.getString(3);
                int productType = rs.getInt(4);
                double price = rs.getDouble(5);
                int yearOfMaking = rs.getInt(6);
                String colour = rs.getString(7);
                int productCondition = rs.getInt(8);
                Status status = Status.valueOf(rs.getString(9));
                Product product = new Product(id, name, seller, EnumHandler.getType(productType), price, yearOfMaking, colour, EnumHandler.getCondition(productCondition), status);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * @Author : Linn Borgström
     * Makes a quarie to th DB to get a product by it's condition
     * @param condition what condition the product is in (an enum)
     * @return an arrayList with the products
     * @throws SQLException
     */
    public ArrayList<Product> getProductsByCondition(Condition condition) {
        ArrayList<Product> productByCondition = new ArrayList<>();
        String query = "select * from Product\n" +
                "RIGHT JOIN ProductCondition ON Product.condition = ProductCondition.productConditionId\n" +
                "LEFT JOIN ProductTypes ON Product.typeOfProduct = ProductTypes.ProductTypeId\n" +
                "where  Product.[status] = 'Available' AND ProductCondition.productConditionName =  '" + condition.toString() + "';";
        try{
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String seller = rs.getString(3);
                int productType = rs.getInt(4);
                double price = rs.getDouble(5);
                int yearOfMaking = rs.getInt(6);
                String colour = rs.getString(7);
                int productCondition = rs.getInt(8);
                Status status = Status.valueOf(rs.getString(9));
                Product product = new Product(id, name, seller, EnumHandler.getType(productType), price, yearOfMaking, colour, EnumHandler.getCondition(productCondition), status);
                productByCondition.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
    public ArrayList<Product> getProductsByTypeOfProduct(TypeOfProduct typeOfProduct) {
        ArrayList<Product> productByType = new ArrayList<>();

        String query = "Select * from Product\n" +
                "RIGHT JOIN ProductCondition ON Product.condition = ProductCondition.productConditionId\n" +
                "LEFT JOIN ProductTypes ON Product.typeOfProduct = ProductTypes.ProductTypeId\n" +
                "where  Product.[status] = 'Available' AND ProductTypes.productTypeName = '" + typeOfProduct.toString() + "';";
        try{
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String seller = rs.getString(3);
                int productType = rs.getInt(4);
                double price = rs.getDouble(5);
                int yearOfMaking = rs.getInt(6);
                String colour = rs.getString(7);
                int productCondition = rs.getInt(8);
                Status status = Status.valueOf(rs.getString(9));
                Product product = new Product(id, name, seller, EnumHandler.getType(productType), price, yearOfMaking, colour, EnumHandler.getCondition(productCondition), status);
                System.out.println(product.getId() + ", "+ product.getCondition());
                productByType.add(product);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
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
            callableStatement.setInt(3, product.getTypeAsInt(product.getTypeOfProductName()));
            callableStatement.setDouble(4, product.getPrice());
            callableStatement.setInt(5, product.getYearOfMaking());
            callableStatement.setString(6, product.getColour());
            callableStatement.setInt(7, product.getConditionAsInt(product.getCondition()));
            callableStatement.setString(8, product.getStatus().toString());
            callableStatement.execute();

            return true;
        }catch (SQLException ex) {
            System.out.println("---> Error add product to DB: \n" + ex.getMessage() + "\n");
            return false;
        }
    }

    public ArrayList<Product> getAllUnavailableProducts(User user) {
        ArrayList<Product> unavailableProducts = new ArrayList<>();
        try {
            String query = "select * from Product\n" +
                    "RIGHT JOIN ProductCondition ON Product.condition = ProductCondition.productConditionId\n" +
                    "LEFT JOIN ProductTypes ON Product.typeOfProduct = ProductTypes.ProductTypeId\n" +
                    "where  Product.[status] = 'Unavailable' AND seller = '" + user.getUserName() + "'; ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String seller = rs.getString(3);
                int productType = rs.getInt(4);
                double price = rs.getDouble(5);
                int yearOfMaking = rs.getInt(6);
                String colour = rs.getString(7);
                int productCondition = rs.getInt(8);
                Status status = Status.valueOf(rs.getString(9));
                Product product = new Product(id, name, seller, EnumHandler.getType(productType), price, yearOfMaking, colour, EnumHandler.getCondition(productCondition), status);
                unavailableProducts.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unavailableProducts;
    }

    public boolean changeProductStatus(int productId, Status newStatus) {
        try {
            String query = "UPDATE [Product] SET Status = '"+newStatus.toString() +"' WHERE productId = " + productId +";";
            statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
            return false;
        }
        return true;
    }


    public boolean getNotification(User user) {
        try{
            String query = "Select Product.productId, Product.name, Product.Seller, Product.typeOfProduct, Product.price, Product.YearOfMaking, Product.colour, Product.condition, Product.status\n" +
                    "from Product \n" +
                    "left join ProductTypes on Product.typeOfProduct = ProductTypes.productTypeId\n" +
                    "left join UserProductType on ProductTypes.productTypeId = UserProductType.typeOfProductId\n" +
                    "where UserProductType.username = '"+ user.getUserName() +"' AND Product.[status] = 'Available' AND Product.dateAdded BETWEEN '" + user.getLastLogIn() + "' AND GETDATE();";
            if(statement.executeQuery(query) == null){
                return false;
            }

        } catch (SQLException throwables) {
            System.out.println("wrong");
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}
