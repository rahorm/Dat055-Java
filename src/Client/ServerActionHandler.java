package Client;

import Common.ChatData;
import Common.ChatMemberData;
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
                System.out.println("User has been created: " + created);

                if(created){
                    facade.setStatusMessage("Success!");
                    facade.updateAvailableChatIds();
                }
                else if(!created) {
                    facade.setStatusMessage("Failed!");
                }

                break;

            case ADD_CHATROOM:
                ChatData updated = (ChatData) request.getData();
                facade.changeActiveRoom(updated.getChatId());
                facade.addMember(facade.getActiveUser(), updated.getChatId());

            break;

            case DELETE_CHATROOM:
                String chatRoom = (String) request.getData();
                System.out.println("Chatroom deleted: " + chatRoom);
                break;

            case ADD_MEMBER:
                ChatMemberData member = (ChatMemberData) request.getData();
                System.out.println("Member added: " + member);
                facade.updateMemberList(member.getUsername());
                break;

            case CHECK_USER:
                boolean exists = (boolean) request.getData();
                System.out.println("User exists: " + exists);
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
                facade.setStatusMessage("Failed!");
                break;

            default:
                System.out.println("Unhandled request type: " + request.getType());
        }

    }
}