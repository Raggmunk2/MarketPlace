package dataAccessLayer.repositories;
import dataAccessLayer.ConnectionToDB;
import shared.*;

import java.sql.*;
import java.util.ArrayList;

public class OrderRepository {
    private Connection connection;
    private UserRepository userRepository;

    public OrderRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            connection = connectionToDB.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Order> getOrderHistory(User thisBuyer)  {
        ArrayList<Order> orderList = new ArrayList<>();
        try {
           String selectOrderHistory = "Select buyer, [name],Seller, typeOfProduct,price," +
                                "condition from [dbo].[Order] join [dbo].[Product] on[dbo].[Order].productId = [dbo].[Product].productId where buyer ='" +thisBuyer.getUserName() + "';";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(selectOrderHistory);

            while (results.next()) {
                String buyerString = results.getString(1);
                User buyer = userRepository.getUser(buyerString);

                String productName = results.getString(2);
                String sellerString = results.getString(3);
                User seller = userRepository.getUser(sellerString);
                TypeOfProduct typeOfProduct = EnumHandler.getType(results.getInt(4));
                double price = results.getDouble(5);
                Condition condition = EnumHandler.getCondition(results.getInt(6));

                Order order = new Order(buyer, productName, seller, typeOfProduct, price, condition);
                orderList.add(order);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    /**
     * User can add a product to Order. It then becomes unavailable
     * @return true if connection to database was successfull
     */
    public boolean addProductToOrder(Order newOrder){
            try {
                Connection connect = connection;
                CallableStatement callableStatement = connect.prepareCall("{CALL MarketPlace.dbo.sp_addProductToOrder(?, ?, ?)}");
                callableStatement.setString(1, newOrder.getBuyer().getUserName());
                callableStatement.setTimestamp(2, newOrder.getCreatedAt());
                callableStatement.setInt(3, newOrder.getProductId());
                callableStatement.execute();
            } catch (SQLException ex) {
                System.out.println("---> Error add product to Order: \n" + ex.getMessage() + "\n");
                return false;
            }
        return true;
    }

    public boolean removeOrder(Order order) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM [Order] WHERE orderId = " + order.getOrderId() +"';";
            statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
            return false;
        }
        return true;
    }

    public ArrayList<Order> getOrdersToConfirm(User thisSeller)  {
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            String selectOrderHistory = ""; //TODO Write
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(selectOrderHistory);

            while (results.next()) {
                String buyerString = results.getString(2);
                User buyer = userRepository.getUser(buyerString);

                String productName = results.getString(6);
                String sellerString = results.getString(7);
                User seller = userRepository.getUser(sellerString);
                TypeOfProduct typeOfProduct = EnumHandler.getType(results.getString(4));
                double price = results.getDouble(9);
                Condition condition = EnumHandler.getCondition(results.getInt(12));

                Order order = new Order(buyer, productName, seller, typeOfProduct, price, condition);
                orderList.add(order);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
