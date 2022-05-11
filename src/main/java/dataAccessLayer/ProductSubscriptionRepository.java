package dataAccessLayer;

import java.sql.*;
import java.util.ArrayList;

public class ProductSubscriptionRepository {

    private Connection connection;
    private Statement statement;

    /**
     * @Author : Frida Jacobsson
     */
    public ProductSubscriptionRepository() throws SQLException {
        connection = new ConnectionToDB().getConnection();
        statement = connection.createStatement();
    }

    public boolean addNewSubscription(int typeOfProduct, String username) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL MarketPlace.dbo.sp_AddSubscription(?,?)}");
            callableStatement.setInt(1, typeOfProduct);
            callableStatement.setString(2, username);
            callableStatement.execute();
            if (callableStatement.getInt(1) != 0) {
                return false;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
            return false;
        }
        return true;
    }

    public boolean removeSubscription(int typeOfProductId, String username) {
        try {
            String query = "DELETE FROM [UserProductType] WHERE typeOfProductId = " + typeOfProductId + " AND username = '" + username + "';";
            statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
            return false;
        }
        return true;
    }

    public ArrayList<String> getUsersWithSubscription(int typeOfProductId) {
        ArrayList<String> users = new ArrayList<>();
        try {

            String query = "SELECT username FROM [UserProductType] WHERE typeOfProductId = " + typeOfProductId + ";";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String username = resultSet.getString(1);
                users.add(username);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
            return null;
        }
        return users;
    }
}
