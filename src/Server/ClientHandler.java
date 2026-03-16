package  Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This is where data is recieved from the client when a broadcastMessage() is called in the class ServerHandler.
 * ClientHandler.run() is then called and it calls ClientActionHandler.handle() where all the logic exists for each kind of action
 * this server is meant to deal with.
 *
 * ClientActionHandler.handle() returns an object which is broadcasted back to the clients so that they know something has changed on the database
 * */
public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private Socket socket;
    private ObjectInputStream objectInputstream;
    private ObjectOutputStream objectOutputStream;
    private ClientActionHandler clientActionHandler;

    /**
     * Creates a new ClientHandler and initializes the socket connection, sets up the input and output streams.
     *
     * @param socket the socket representing the connection between the server and the client
     */
    public ClientHandler(Socket socket){
        try {
            System.out.println("Creating new client");
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputstream = new ObjectInputStream(socket.getInputStream());
            this.clientActionHandler = new ClientActionHandler();

            clientHandlers.add(this);

        } catch (IOException e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    /**
     * This method reads serialized objects sent by the client through the ObjectInputStream, processes them using
     * the clientActionHandler, and broadcasts the resulting response to all connected clients.
     * The loop runs as long as the socket remains connected.
     *
     * IOException : when disconnected
     * ClassNotFoundException : when disconnected
     */

    @Override
    public void run(){
        Object objectFromClient;

        while(socket.isConnected()){
            try {
                objectFromClient = objectInputstream.readObject();
                System.out.println(objectFromClient);
                Object output = clientActionHandler.handle(objectFromClient);

                if(output != null){
                    broadcastMessage(output);
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("ERROR: "+e.getMessage());
                break;
            }
        }
    }

    /**
     * This method writes the given object to the ObjectOutputStream so it
     * can be transmitted to the client. The stream is flushed after writing.
     *
     * @param obj the object to be sent to the client.
     */
    public void broadcastMessage(Object obj){
        try {
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();


        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * This method removes a clientHandler.
     *
     */
    public void removeClientHandler(){
        clientHandlers.remove(this);
    }

}
