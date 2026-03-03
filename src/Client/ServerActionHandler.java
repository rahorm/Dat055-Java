package Client;

//import Common.MsgHistoryWrapper;
import Common.RequestWrapper;
import Model.ChatRoomFacade;
import Model.ChatRoomModel;
import Other.Message;

import java.util.ArrayList;

public class ServerActionHandler {

    public ServerActionHandler() {
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
                ChatRoomFacade facade = Model.ChatRoomFacade.getInstance();
                facade.setHistory(history);
                break;

            case ADD_USER:
                String user = (String) request.getData();
                System.out.println("User received: " + user);
                //ChatRoomFacade facade = Model.ChatRoomFacade.getInstance();
                //facade.addUser(user);
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

            //Tillägg
            case ADD_CHAT_MEMBER:
                String userName = (String) request.getData();
                System.out.println("Chat member received: " + userName);
                //facade.addMember(userName);
                break;

            case CHECK_USER:
                boolean exists = (boolean) request.getData();
                System.out.println("User exists: " + exists);
                //facade.addMember(userName);
                break;

            case GET_AVAILABLE_CHATS:
                ArrayList<String> data = (ArrayList<String>) request.getData();
                System.out.println("Available chats received: " + data);
                // facade.getInstance().setAvailableChats(data);
                break;

            case LOGIN:
                boolean successful = (boolean) request.getData();
                if(!successful){
                    ChatRoomFacade f = Model.ChatRoomFacade.getInstance();
                    f.setActiveUser(""); //@todo ska det finnas en metod för att ta bort användare (t.ex. när man loggar ut)?
                    break;
                }
                //@todo meddela ui att användaren är inloggad
                break;

            default:
                System.out.println("Unhandled request type: " + request.getType());
        }

    }
}

    /*public void handle(Object obj) {
        Object objToReturn = null;

        if (obj instanceof MsgHistoryWrapper input) {

            System.out.println("Output: " + input.toString());
            ArrayList<Message> history;
            history = (ArrayList<Message>) input.getMsgHistory();
            System.out.println("Message history received: " + history);
            ChatRoomFacade facade = Model.ChatRoomFacade.getInstance();
            facade.setHistory(history);
        }


    }*/
