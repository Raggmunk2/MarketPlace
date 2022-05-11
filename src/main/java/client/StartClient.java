package client;

import client.controller.Controller;

import java.sql.SQLException;
import java.text.ParseException;

public class StartClient {

    public static void main(String[] args) throws SQLException, ParseException, ClassNotFoundException {
        Controller controller = new Controller();
    }
}
