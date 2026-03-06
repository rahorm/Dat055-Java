package Client;

//import Common.MsgHistoryWrapper;
import Common.ChatData;
import Common.RequestWrapper;
import Common.UserData;
import Model.ChatRoomFacade;
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

        ChatRoomFacade facade = Model.ChatRoomFacade.getInstance();

        switch (request.getType()) {

            case GET_MESSAGES:
                ArrayList<Message> history = (ArrayList<Message>) request.getData();
                System.out.println("Message history received: " + history);
                facade.setHistory(history);
                break;

            case CREATE_USER:
                boolean created = (boolean) request.getData();
                //skicka vidare till facaden
                System.out.println("User has been created: " + created);

                if(created){
                    facade.setStatusMessage("Success!");
                    facade.updateAvailableChatIds();
                }
                else if(!created){
                    facade.setStatusMessage("Failed!");
                }
                break;

            case ADD_CHATROOM:
                String room = (String) request.getData();
                System.out.println("Chat has been created: " + room);
                //ytterligare actions?
                break;

            case DELETE_CHATROOM:
                String chatRoom = (String) request.getData();
                System.out.println("Chatroom deleted: " + chatRoom);
                //yterligare actions?
                break;

            case ADD_CHAT_MEMBER:
                ArrayList<String> memberList = (ArrayList<String>) request.getData();
                System.out.println("Chat members updated: " + memberList);
                //vad ska göras med den uppdaterade members listan?
                break;

            case CHECK_USER:
                boolean exists = (boolean) request.getData();
                System.out.println("User exists: " + exists);
                //fler actions?
                break;

            case GET_AVAILABLE_CHATS:
                ArrayList<ChatData> data = (ArrayList<ChatData>) request.getData();
                System.out.println("Available chats received: " + data);
                facade.setAvailableChats(data);
                break;

            case LOGIN:
                Object[] loginInfo = (Object[]) request.getData();
                if((boolean) loginInfo[0]){
                    UserData userData = (UserData) loginInfo[1];
                    facade.setActiveUser(userData.getUsername());
                    facade.setStatusMessage("Success!");
                    facade.updateAvailableChatIds();
                    System.out.println("logged in clientside");
                    break;
                }
                //@todo meddela ui att användaren är inloggad
                facade.setStatusMessage("Failed!");
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
