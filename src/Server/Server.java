package  Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Server instance;
    private ServerSocket serverSocket;

    /**
     * If Server instance is null, create a new server instance and return
     * @return instance
     */
    public static Server getInstance(){
        if(instance == null){
            try{
                instance = new Server(new ServerSocket(3356));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return instance;
    }

    private Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    /**
     * This method is used to startServer
     * while serverSocket is not closed, it connects a new client to the server
     */
    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept(); //blocking method. stops program until a client connects, returning the socket used to communicate with client (stored)
                System.out.println("New client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes the serversocket if it is open
     */
    public void closeServerSocket() {
        try {
            if(serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = Server.getInstance();
        server.startServer();
    }
}
