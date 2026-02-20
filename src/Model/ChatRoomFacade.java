package Model;

import Client.ServerConnection;
import Other.IdGenerator;
import Other.Message;
import Other.User;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;

public final class ChatRoomFacade extends Observable {
    private static ChatRoomFacade instance;
    private ChatRoomModel model;
    private ServerConnection serverConnection;

    public ChatRoomFacade(ChatRoomModel model) {
        this.model = model;
        try {
            this.serverConnection = new ServerConnection(new Socket("localhost", 3356));
        } catch (IOException e) {
            System.out.println("Error:"+e.getMessage());
        }
    }

    public static ChatRoomFacade getInstance() {
        if (instance == null){
            instance = new ChatRoomFacade(new ChatRoomModel(1, "User1"));
        }
        return instance;
    }

    /**
     * User wants to change an active room, the chatroom that user wants to
     * open will be identified by chatID, this chatID should be sent as a parameter
     * when it's called in Control.
     *
     * @param chatID of a chatroom that user wants to open
     */
    public void changeActiveRoom(int chatID) {
        model.changeActiveRoom(chatID);

        setChanged(); // Apparently it should be used before using notifyObservers

        notifyObservers(); // ingen parameter

    }

    public ArrayList<Message> getMSGList(){
        // listan av meddelande
        return model.retriveMSGList();
    }


    /**
     * Uer writes a new message. Then this message information will be passed to the server
     * @param msg message that user write
     * @param username user that wrote this message
     */
    public void StoreMsg(String msg, String username){

        serverConnection.sendMsg(new Message(model.getActiveUser(), model.getChatID(), msg));

    }

/**
 * Adds a user to the member list of this chat room.
 * If the user is already a member, nothing
 *
 * @param user user to add; must not be  null
 * @throws IllegalArgumentException if user is null
 */
public void addMember(String user) {
    if (user == null) {
        throw new IllegalArgumentException("user must not be null");
    }

    ArrayList<String> members = model.retrieveUserList();
    if (!members.contains(user)) {
        model.addUser(user);
    }
}


/**
 * Removes a user from the member list of this chat room.
 * If the user is not a member, nothing happens.
 *
 * @param user user to remove; must not be  null
 * @throws IllegalArgumentException if  user is null
 */
public void removeMember(String user) {
    if (user == null) {
        throw new IllegalArgumentException("user must not be null");
    }
    model.removeUser(user);
}

/**
 * Adds a new message to this chat room.
 *
 * @param message message to add; must not be null
 * @throws NullPointerException if  message is  null
 */
public void addMessage(Message message) {
    if (message == null) {
        throw new IllegalArgumentException("message must not be null");
    }
    ArrayList<Message> messages = model.retriveMSGList();
    messages.add(message);
}

/**
 * Removes a message from this chat room.
 * If the message is not found, nothing happens.
 *
 * @param message message to remove; must not be null
 * @throws IllegalArgumentException if message is null
 */
public void removeMessage(Message message) {
    if (message == null) {
        throw new IllegalArgumentException("message must not be null");
    }
    ArrayList<Message> messages = model.retriveMSGList();
    messages.remove(message);
}
}





