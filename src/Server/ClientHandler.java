package  Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This is where data is recieved from the client when a broadcastMessage() is called in the class ServerHandler.
 * ClientHandler.run() is then called and it calls ClientActionHandler.handle() where all the logic exists for each kind of action
 * this server is meant to deal with.
 *
 * The second time the program flow arrives at this class is when ClientActionHandler.handle() returns a response such as
 * "yes the login was correct". Then ClientHandler.broadcastMessage() is called and every client connected to the server is notified that
 * an update has happened on the server.
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
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());  //bytestream wrapped in charstream, used to send things
            this.objectInputstream = new ObjectInputStream(socket.getInputStream()); //used to recieve things
            this.clientActionHandler = new ClientActionHandler();

            clientHandlers.add(this);

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run(){ //want to run this on a separate thread from application handling since listening to messages is a blocking task
        Object objectFromClient;

        while(socket.isConnected()){
            try {
                objectFromClient = objectInputstream.readObject();
                System.out.println(objectFromClient);
                Object output = clientActionHandler.handle(objectFromClient);
                //skickar nu responsen till alla clients som är anslutna, får ta beslut om huruvida det är korrekt eller inte
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
