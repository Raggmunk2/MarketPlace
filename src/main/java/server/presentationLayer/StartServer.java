package server.presentationLayer;

import buisnessLogicLayer.ResponseHandler;
import dataAccessLayer.ConnectionToDB;

import java.sql.Connection;

public class  StartServer {

    public static void main(String[] args) {
        Connection connection = new ConnectionToDB().getConnection();
        System.out.println(connection);
        ResponseHandler requestHandler = new ResponseHandler();
        Server server = new Server(6890, requestHandler);
    }
}
