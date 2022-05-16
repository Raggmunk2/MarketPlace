package client.controller;

import shared.RequestMessage;
import shared.ResponseMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection extends Thread{

    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ServerConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ResponseMessage sendRequest(RequestMessage request) {
        ResponseMessage response = null;
        try {
            socket = new Socket(host, port);
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(request);
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
            response = (ResponseMessage) input.readObject();

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