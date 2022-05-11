package dataAccessLayer.repositories;

import dataAccessLayer.ConnectionToDB;
import dataAccessLayer.repositories.OrderRepository;
import shared.User;

import java.sql.*;


public class UserRepository {

    private Connection connection;
    private User user = null;

    public UserRepository() {
        connection = new ConnectionToDB().getConnection();
    }

    public User getUser(String username) {
        try {
            String queryLogin = "SELECT * FROM [User] WHERE username = '" + username + "';";
            ResultSet results;
            Statement statement = connection.createStatement();
            results = statement.executeQuery(queryLogin);

            while (results.next()) {
                String dataBaseUsername = results.getString("username");
                String dataBasePassword = results.getString("password");
                String firstname = results.getString("firstname");
                String lastname = results.getString("lastname");
                Date dateOfBirth = results.getDate("dateOfBirth");
                String email = results.getString("email");
                user = new User("No user exists", dataBasePassword, firstname, lastname, dateOfBirth, email);
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User checkLogin(String username, String password) {
        try {
            String queryLogin = "SELECT * FROM [User] WHERE username = '" + username + "';";
            ResultSet results;

            String dataBaseUsername = "";
            String dataBasePassword = "";
            String firstname = "";
            String lastname = "";
            Date DoB = null;
            String email = "";

            Statement statement = connection.createStatement();
            results = statement.executeQuery(queryLogin);

            while (results.next()) {
                dataBaseUsername = results.getString("username");
                dataBasePassword = results.getString("password");
                firstname = results.getString("firstname");
                lastname = results.getString("lastname");
                DoB = results.getDate("dateOfBirth");
                email = results.getString("email");
            }

            if (dataBasePassword.equals(password)) {
                //Skapar en user med data från databasen
                user = new User(dataBaseUsername, dataBasePassword, firstname, lastname, DoB, email);
            } else {
                //Skapar en user även om lösenordet inte matchar, detta för att det som skickas tillbaka till klienten inte ska vara null
                //användarnamnet är dock "No user exists" som kollas sedan på klient-sidan för att veta om användaren inte finns i DB.
                user = new User("No user exists", dataBasePassword, firstname, lastname, DoB, email);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return user;
    }

    public boolean registerNewUser(User user) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL MarketPlace.dbo.sp_userCreateAccount(?,?,?,?,?,?)}");

            callableStatement.setString(1, user.getFirstName());
            callableStatement.setString(2, user.getLastName());
            callableStatement.setObject(3, user.getDateOfBirth());
            callableStatement.setString(4, user.geteMail());
            callableStatement.setString(5, user.getUserName());
            callableStatement.setString(6, user.getPassword());
            callableStatement.execute();
            System.out.println("You successfully register an account!");
        } catch (SQLException e) {
            System.out.println("Please try again!");
            return false;
        }
        return true;
    }
}
