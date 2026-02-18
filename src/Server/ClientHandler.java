package  Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); //keeps track of all clients this is publisher/thing that notifies

    private Socket socket;
    private ObjectInputStream objectInputstream;
    private ObjectOutputStream objectOutputStream;
    private ClientActionHandler actionHandler;

    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());  //bytestream wrapped in charstream, used to send things
            this.objectInputstream = new ObjectInputStream(socket.getInputStream()); //used to recieve things
            this.actionHandler = new ClientActionHandler();

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
                Object output = actionHandler.handle(objectFromClient);
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
