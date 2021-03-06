package dataAccessLayer.repositories;

import dataAccessLayer.ConnectionToDB;
import shared.User;

import java.sql.*;
import java.time.LocalDateTime;


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
                String lastLogIn = results.getString("lastLogIn");
                user = new User(dataBaseUsername, dataBasePassword, firstname, lastname, dateOfBirth, email, lastLogIn);
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
            String lastLogIn = "";

            Statement statement = connection.createStatement();
            results = statement.executeQuery(queryLogin);

            while (results.next()) {
                dataBaseUsername = results.getString("username");
                dataBasePassword = results.getString("password");
                firstname = results.getString("firstname");
                lastname = results.getString("lastname");
                DoB = results.getDate("dateOfBirth");
                email = results.getString("email");
                lastLogIn = results.getString("lastLogIn");
            }

            if (dataBasePassword.equals(password)) {
                //Skapar en user med data fr??n databasen
                user = new User(dataBaseUsername, dataBasePassword, firstname, lastname, DoB, email, lastLogIn);
                //updateLastLoggedIn(user.getUserName());
                System.out.println("UserRepository - lastLogin: " + lastLogIn);
            } else {
                //Skapar en user ??ven om l??senordet inte matchar, detta f??r att det som skickas tillbaka till klienten inte ska vara null
                //anv??ndarnamnet ??r dock "No user exists" som kollas sedan p?? klient-sidan f??r att veta om anv??ndaren inte finns i DB.
                user = new User("No user exists", dataBasePassword, firstname, lastname, DoB, email, lastLogIn);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return user;
    }

    public boolean updateLastLoggedIn(String username){
        try {
            String query = "UPDATE [User]\n" +
                    "SET lastLogIn = GETDATE()\n" +
                    "WHERE username = '" + username +"';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }

    }

    public String getLastLoggedIn(String username){
        String lastLoggedIn = null;
        try{
            String query = "SELECT [lastLogIn]\n" +
                    "FROM [User]" +
                    "WHERE username = '" + username +"';";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                lastLoggedIn = results.getString(1);
            }

        } catch (SQLException e){
            System.out.println(e);
        }
        return lastLoggedIn;
    }

    public boolean registerNewUser(User user) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL MarketPlace.dbo.sp_userCreateAccount(?,?,?,?,?,?)}");

            callableStatement.setString(1, user.getFirstName());
            callableStatement.setString(2, user.getLastName());
            callableStatement.setObject(3, user.getDateOfBirth());
            callableStatement.setString(4, user.getMail());
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
