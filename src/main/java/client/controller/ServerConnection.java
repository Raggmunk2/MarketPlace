package client.controller;

import shared.RequestMessage;
import shared.ReturnMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {

    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ServerConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ReturnMessage sendRequest(RequestMessage request) {
        ReturnMessage response = null;
        try {
            socket = new Socket(host, port);
            System.out.println("Connected to server on port: " + port);
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(request);
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println(request + " was sent");
            response = (ReturnMessage) input.readObject();
            System.out.println(response + "came back from server!");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            socket.close();
            output.close();
            input.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }
}