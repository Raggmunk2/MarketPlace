package server.presentationLayer;

import buisnessLogicLayer.ResponseHandler;
import shared.RequestMessage;
import shared.ResponseMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private final Thread serverThread = new Thread(this);
    private ServerSocket serverSocket = null;
    private ExecutorService executor;
    private boolean isServerRunning;
    private ResponseHandler responseHandler;

    public Server(int port, ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        executor = Executors.newFixedThreadPool(10);
        try {
            serverSocket = new ServerSocket(port);
            isServerRunning = true;
            serverThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Server is listening");
        while (isServerRunning) {
            try {
                Socket socket = serverSocket.accept();
                ClientRequest task = new ClientRequest(socket);
                executor.submit(task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println("Server is now stopped");
    }

    class ClientRequest implements Runnable {

        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private Socket socket;

        public ClientRequest(Socket socket) {
            try {
                this.socket = socket;
                this.ois = new ObjectInputStream(socket.getInputStream());
                this.oos = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                RequestMessage request = (RequestMessage) ois.readObject();
                ResponseMessage response = responseHandler.handleRequest(request);
                oos.writeObject(response);
                oos.flush();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                    oos.close();
                    socket.close();

                } catch (IOException ie) {
                    System.out.println("Socket Close Error");
                }
            }
        }
    }
}




