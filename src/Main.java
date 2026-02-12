import Other.ServerConnection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args){
        try {
            //InetAddress address = InetAddress.getByName("192.168.1.28");
            Socket socket = new Socket("192.168.1.28", 3356);
            ServerConnection conn = new ServerConnection(socket);
            conn.connectionTest();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
