package presentationLayer;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ServerInterface {
    public void startMessage(BufferedReader is, Socket socket) throws IOException {
        is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream, true);
        String startMessage = "Hej"; //:TODO change message to suitable one
        writer.println(startMessage);
    }
    public HashMap receiveMessage(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String message = bufferedReader.readLine();
        System.out.println("receive message from client: " + message);
        HashMap<String,BufferedReader> map = new HashMap<>();
        map.put(message,bufferedReader);
        return map;
    }

    public String updateMessage(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }
}
