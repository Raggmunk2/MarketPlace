package dataAccessLayer;
import shared.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderHistoryRepository {
    private Connection connection;
    private Statement statement;
    private ArrayList<String> orderHistoryArrList;

    public OrderHistoryRepository(User user) throws SQLException {
        ConnectionToDB connectionToDB = new ConnectionToDB();
        connection = connectionToDB.getConnection();
        statement = connection.createStatement();
        orderHistoryArrList = getOrderHistory(user);
    }
    private ArrayList<String> getOrderHistory(User user) throws SQLException {
        ArrayList<String> tempArrayList = new ArrayList<>();
        String selectOrderHistory = "Select * from [dbo].[Order] join [dbo].[Product] on" +
                "[dbo].[Order].productId = [dbo].[Product].productId where buyer = '" + user.getUserName() + "'";
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(selectOrderHistory);

        String finalString = null;
        while (results.next()){
            String buyer = results.getString(2);
            String productName = results.getString(6);
            String seller = results.getString(7);
            String typeOfProduct = results.getString(8);
            String price = results.getString(9);
            String condition = results.getString(12);

            StringBuilder sb = new StringBuilder();

            if (buyer.equals(user.getUserName())){
                sb.append("Buyer: " + buyer + ", ");
                sb.append("Product name: " + productName + ", ");
                sb.append("Seller: " + seller + ", ");
                sb.append("Type of product: " + typeOfProduct + ", ");
                sb.append("Price: " + price + ", ");
                sb.append("Condition: " + condition + " ");
                finalString = sb.toString();
            }
            tempArrayList.add(finalString);
        }
        return tempArrayList;
    }
    public ArrayList<String> getStringOrderHistory(){
        return orderHistoryArrList;
    }
}
