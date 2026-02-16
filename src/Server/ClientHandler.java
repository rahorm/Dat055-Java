package  Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); //keeps track of all clients this is publisher/thing that notifies

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private ActionHandler actionHandler;

    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));  //bytestream wrapped in charstream, used to send things
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //used to recieve things
            this.actionHandler = new ActionHandler();

            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER: "+clientUsername+" has entered the chat");

        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run(){ //want to run this on a separate thread from application handling since listening to messages is a blocking task
        String messageFromClient;

        while(socket.isConnected()){
            try {
                messageFromClient = bufferedReader.readLine();
                System.out.println(messageFromClient);
                broadcastMessage(messageFromClient);
                /*
                * ovan får vi in ett object från en client
                * baserat på detta objektet ska en strategi väljas och köras
                * Här nånstans ska actionhandler kallas
                * */


            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend){
        for(ClientHandler ch : clientHandlers){
            try {
                if(!ch.clientUsername.equals(clientUsername)){
                    ch.bufferedWriter.write(messageToSend);
                    ch.bufferedWriter.newLine();
                    ch.bufferedWriter.flush();
                }

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER: "+clientUsername+" has left the chat");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try {
            if(bufferedReader != null){ //to avoid null pointer errors
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
