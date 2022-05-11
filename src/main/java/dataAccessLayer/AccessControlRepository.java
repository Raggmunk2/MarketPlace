package dataAccessLayer;

import shared.User;

import java.sql.*;
import java.util.ArrayList;


public class AccessControlRepository {

    private Connection connection;
    private Statement statement;
    private User user = null;
    private OrderHistoryRepository orderHistoryRepository;
    private boolean registrationSuccess;

    public AccessControlRepository() {
        connection = new ConnectionToDB().getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User checkLogin(String username, String password) {
        String queryLogin = "SELECT * FROM [User] WHERE username = '" + username + "';";
        ResultSet results = null;
        try {
            Statement statement = connection.createStatement();
            results = statement.executeQuery(queryLogin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (results.next()) {
                User user = new User(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getDate(5), results.getString(6));
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
       /* if(loginSuccess){
            ArrayList<Product> productToConfirm = pr.getAllUnavailableProducts(user.getUserName());
            if(productToConfirm != null){

            }
*//*
        }else{
            System.out.println("Wrong, please try again!!");
            loginSuccess = false;
        }*/
        return null;
    }

    public boolean addUserAccountToDatabase(String newUserFirstname, String newUserLastName, Date newUserdateOfBirth, String newUserEmail, String newUserName, String newUserPassword) {

        try {
            Connection connect = connection;

            CallableStatement callableStatement = connection.prepareCall("{CALL MarketPlace.dbo.sp_userCreateAccount(?,?,?,?,?,?)}");

            callableStatement.setString(1, newUserFirstname);
            callableStatement.setString(2, newUserLastName);
            callableStatement.setObject(3, newUserdateOfBirth);
            callableStatement.setString(4, newUserEmail);
            callableStatement.setString(5, newUserName);
            callableStatement.setString(6, newUserPassword);
            callableStatement.execute();
            System.out.println("You successfully register an account!");
            registrationSuccess = true;

        } catch (SQLException e) {
            System.out.println("Please try again!");
            //System.out.println(e.getMessage());
        }
        return registrationSuccess;
    }

    public boolean getOrderHistory() throws SQLException {
        orderHistoryRepository = new OrderHistoryRepository(user);
        ArrayList<String> listToAppend = orderHistoryRepository.getStringOrderHistory();
        if (listToAppend != null) {
            for (int i = 0; i < listToAppend.size(); i++) {
                System.out.println(listToAppend.get(i));
            }
            registrationSuccess = true;
        } else {
            System.out.println("There is no order history for this user");
            registrationSuccess = false;
        }
        return registrationSuccess;
    }

    private void setUser(String newUserFirstname, String newUserLastName, Date newUserdateOfBirth, String newUserEmail, String newUserName, String newUserPassword) {
        long millis = System.currentTimeMillis();
        java.sql.Date lastLogin = new java.sql.Date(millis);
        user = new User(newUserName, newUserPassword, newUserFirstname, newUserLastName, newUserdateOfBirth, newUserEmail, lastLogin, true);
    }

}
