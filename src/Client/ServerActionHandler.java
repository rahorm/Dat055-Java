package Client;

import Common.RequestWrapper;
import Other.Message;

import java.util.ArrayList;

public class ServerActionHandler {
    Model.ChatRoomFacade facade;

    public ServerActionHandler() {
        facade = Model.ChatRoomFacade.getInstance();
    }

    public void handle(Object obj) {

        if (!(obj instanceof RequestWrapper request)) {
            System.out.println("Unknown object received: " + obj);
            return;
        }

        System.out.println("Received request: " + request);

        switch (request.getType()) {

            case GET_MESSAGES:
                ArrayList<Message> history;
                history = (ArrayList<Message>) request.getData();
                System.out.println("Message history received: " + history);
                // modelFacade.setHistory(history);
                break;

            case ADD_MESSAGE:
                Message msg = (Message) request.getData();
                System.out.println("Message received: " + msg);
                // modelFacade.sendMessage(msg);
                break;

            case ADD_USER:
                String user = (String) request.getData();
                System.out.println("User received: " + user);
                // modelFacade.addUser(user);
                break;

            case ADD_CHATROOM:
                String room = (String) request.getData();
                System.out.println("ChatRoom received: " + room);
                // modelFacade.addChatRoom(room);
                break;

            case DELETE_CHATROOM:
                String chatRoom = (String) request.getData();
                System.out.println("Delete chatroom: " + chatRoom);
                // modelFacade.deleteChatRoom(roomName);
                break;

            default:
                System.out.println("Unhandled request type: " + request.getType());
        }
    }


//    public void handle(Object obj){
//        Object objToReturn = null;
//
//        if(obj instanceof MsgHistoryWrapper input){
//
//            System.out.println("Output: "+input.toString());
//            // MODEL FACADE CONNECTION? input.getMsgHistory();
//        }


    }
