package  Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); //keeps track of all clients this is publisher/thing that notifies

    private Socket socket;
    private ObjectInputStream objectInputstream;
    private ObjectOutputStream objectOutputStream;
    private ActionHandler actionHandler;

    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());  //bytestream wrapped in charstream, used to send things
            this.objectInputstream = new ObjectInputStream(socket.getInputStream()); //used to recieve things
            this.actionHandler = new ActionHandler();

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
                actionHandler.handle(objectFromClient);
                broadcastMessage(objectFromClient);
                /*
                * ovan får vi in ett object från en client
                * baserat på detta objektet ska en strategi väljas och köras
                * Här nånstans ska actionhandler kallas
                * */


            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public void broadcastMessage(Object obj){
        /*for(ClientHandler ch : clientHandlers){
            try {
                if(!ch.clientUsername.equals(clientUsername)){
                    ch.bufferedWriter.write(messageToSend);
                    ch.bufferedWriter.newLine();
                    ch.bufferedWriter.flush();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }*/
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
    }

}
