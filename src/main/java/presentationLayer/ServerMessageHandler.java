package presentationLayer;

import buisnessLogicLayer.ProductLogic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class ServerMessageHandler {
    public static void handleMessage(String message, Socket socket) throws SQLException, ClassNotFoundException, IOException {
        String response = "";
        ProductLogic productLogic = new ProductLogic();
        switch (message){
            case "1" :
                response = "Nånting"; // TODO change messages on all cases
                break;
            case "2" :
                response = "Nånting";
                break;
            case "3" :
                response = "Nånting";
                break;
            case "4" :
                response = "Nånting";
                break;
            case "5" :
                response = "Nånting";
                break;
        }
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream,true);
        writer.println(response);
    }
}


