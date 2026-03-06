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
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); //keeps track of all clients this is publisher/thing that notifies

    private Socket socket;
    private ObjectInputStream objectInputstream;
    private ObjectOutputStream objectOutputStream;
    private ClientActionHandler clientActionHandler;

    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputstream = new ObjectInputStream(socket.getInputStream());
            this.clientActionHandler = new ClientActionHandler();

            clientHandlers.add(this);

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

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
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public void broadcastMessage(Object obj){
        for(ClientHandler ch : clientHandlers){
            try {
                ch.objectOutputStream.writeObject(obj);
                ch.objectOutputStream.flush();


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
    }

}
