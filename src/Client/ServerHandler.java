package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable{

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ServerActionHandler serverActionHandler;

    public ServerHandler(Socket socket){
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());  //bytestream wrapped in charstream, used to send things
            this.objectInputStream = new ObjectInputStream(socket.getInputStream()); //used to recieve things
            this.serverActionHandler = new ServerActionHandler();
        } catch (IOException e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    @Override
    public void run(){ //want to run this on a separate thread from application handling since listening to messages is a blocking task
        Object objectFromServer;

        while(socket.isConnected()){
            try {
                objectFromServer = objectInputStream.readObject();
                System.out.println(objectFromServer); // test
                serverActionHandler.handle(objectFromServer);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("ERROR: "+e.getMessage());
                break;
            }
        }
    }

    public void broadcastMessage(Object obj){
        //System.out.println("broadcasting");
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
            } catch (IOException e) {
                System.out.println("Error: "+e.getMessage());
            }
    }

    // --------- Closing ------------------
    public void close() {
        try {
            if (objectOutputStream != null) objectOutputStream.close();
            if (objectInputStream  != null) objectInputStream.close();
            if (socket != null) socket.close();
        } catch (IOException ignored) { }
    }

}
