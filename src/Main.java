import Controller.Controller;
import Model.ChatRoomFacade;
import View.ChatRoomView;
import Other.*;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args){
        /*System.out.println("andy changed this again");
        ChatRoomFacade model = new ChatRoomFacade();
        Controller controller = new Controller();
        ChatRoomView view = new ChatRoomView(controller, model);*/

        try {
            //InetAddress address = InetAddress.getByName("192.168.1.28");
            Socket socket = new Socket("10.0.33.146", 3356);
            ServerConnection conn = new ServerConnection(socket);
            conn.connectionTest();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
