package presentationLayer;

import buisnessLogicLayer.RequestHandler;
import dataAccessLayer.ConnectionToDB;

import java.sql.Connection;

public class  StartServer {

    public static void main(String[] args) {
        Connection connection = new ConnectionToDB().getConnection();
        System.out.println(connection);
        RequestHandler requestHandler = new RequestHandler();
        Server server = new Server(6890, requestHandler);
    }
}
