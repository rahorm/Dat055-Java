import Client.ServerConnection;
import Controller.Controller;
import Model.ChatRoomFacade;
import Model.ChatRoomModel;
import Other.Message;
import View.ChatRoomView;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args){
        //System.out.println("andy changed this again");
        ChatRoomFacade modelFacade = ChatRoomFacade.getInstance();
        Controller controller = new Controller(modelFacade);
        ChatRoomView view = new ChatRoomView(controller, modelFacade);
    }
}
