import Other.ServerConnection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args){
        try {
            //InetAddress address = InetAddress.getByName("192.168.1.28");/<- changed 13/2-26
            Socket socket = new Socket("10.0.33.146", 3156);
            ServerConnection conn = new ServerConnection(socket);
            conn.connectionTest();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
