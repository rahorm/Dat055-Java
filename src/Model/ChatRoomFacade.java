package Model;

import Client.ServerConnection;
import Other.IdGenerator;
import Other.Message;
import Server.DatabaseConnection;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

public final class ChatRoomFacade extends Observable {
    private static ChatRoomFacade instance;
    private ChatRoomModel model;
    private ServerConnection serverConnection;

    private ChatRoomFacade(ChatRoomModel model) {
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


    /// ----------------------------- ChatRoom <-> UI -----------------------------
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

    /// ----------------------------- ChatRoom <-> Server -----------------------------
    public void createChatRoom() {
        IdGenerator idGen = IdGenerator.getInstance();
        int new_id = idGen.generateId();

        serverConnection.createChatRoom(new_id);
        serverConnection.addChatMember(model.getActiveUser());  // adding myself or inviting a new member?

        // changeActiveRoom to be called either here or in the controller
        changeActiveRoom(new_id);

        // dbconn creatChat has two arguments: chatiD, chatName
        // serverConn has one argument: chatID

    }

    /// ----------------------------- ChatRoom <-> Server -----------------------------
    public void removeChatRoom(int chatID) {

        serverConnection.deleteChatRoom(chatID);
        model.removeChatRoom(chatID);
        setChanged();
        notifyObservers();
    }

    /// ----------------------------- Message <-> UI -----------------------------
    public ArrayList<Message> getMSGList(){
        return model.retriveMSGList(); // how many elements to show?
    }


    /// ----------------------------- Message <-> Server -----------------------------
    /**
     * Uer writes a new message. Then this message information will be passed to the server
     * @param msg message that user write
     * @param username user that wrote this message
     */
    public void storeMsg(String msg, String username){

        serverConnection.sendMsg(new Message(model.getActiveUser(), model.getChatID(), msg));

    }

    public void setHistory(ArrayList<Message> history){
        model.setHistory(history);

        setChanged(); // Apparently it should be used before using notifyObservers
        notifyObservers(); // ingen parameter

    }

    /// ----------------------------- Member <-> Server -----------------------------
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

    serverConnection.addChatMember(user);
}

    /// ----------------------------- Member <-> Server -----------------------------
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

    serverConnection.removeChatMember(user);
}


    /// ----------------------------- Messages <-> UI!  -----------------------------

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

    // model.addMessage(message); <- can't we just use this method call instead of the above two lines?
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


// Duplicated method, existed both in facade and model ???
    public String getActiveUser() {
        return model.getActiveUser();
    }


}





