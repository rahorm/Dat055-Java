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
    // Forwarders till model för UI
    public ArrayList<String> getAvailableChatNames() {
        return model.getAvailableChatNames();
    }

    public ArrayList<Integer> getAvailableChatIds() {
        return model.getAvailableChatIds();
    }


    /// ----------------------------- ChatRoom <-> Server -----------------------------
    public void createChatRoom(String chatName) {
        if (chatName == null || chatName.isEmpty()){
            throw new IllegalArgumentException("chatName must not be null or empty");
        }

        IdGenerator idGen = IdGenerator.getInstance();
        int new_id = idGen.generateId();

        serverConnection.createChatRoom(new_id, chatName);

        String user = model.getActiveUser();

        serverConnection.addChatMember(new_id + ":" + user);  // adding myself
        //Lägg till i available-listorna
        model.addAvailableChat(new_id, chatName);

        // changeActiveRoom to be called either here or in the controller
        changeActiveRoom(new_id);
        setChanged();
        notifyObservers();

        // dbconn creatChat has two arguments: chatiD, chatName
        // + serverConn
    }


    /// ----------------------------- ChatRoom <-> Server -----------------------------
    public void removeChatRoom(int chatID) {

        serverConnection.deleteChatRoom(chatID);
        //model.removeChatRoom(chatID);  this one doesnt need
        //setChanged();
        //notifyObservers();
    }

    public void setAvailableChats(ArrayList<String> idNamePairs) {
        model.setAvailableChats(idNamePairs);
        setChanged();
        notifyObservers();
    }


    /// ----------------------------- Message <-> UI -----------------------------
    public ArrayList<Message> getMSGList(){
        return model.retriveMSGList(); // how many elements to show?
    }





    /// ----------------------------- Message <-> Server -----------------------------
    /**
     * User writes a new message. Then this message will be passed to the server
     * @param msg message that user write
     */
    public void storeMsg(String msg){
        System.out.println("message entered facade");
        serverConnection.sendMsg(new Message(model.getActiveUser(), model.getChatID(), msg));
        System.out.println("message left facade");

    }

    public void setHistory(ArrayList<Message> history){
        model.setHistory(history);
        setChanged();
        notifyObservers();

    }

    public void setActiveUser(String username){
        model.setActiveUser(username);
    }

    // Duplicated method, existed both in facade and model ???
    public String getActiveUser() {
        return model.getActiveUser();
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

    /**
     * Checks if a username is present in the database
     * @param username username to check
     * @return if username is present in database
     */
    public boolean checkUser(String username){
        return serverConnection.checkUserExists(username);
    }

    /**
     * Checks if users credentials matches saved ones
     * @param username username of user trying to log in
     * @param password password user is trying to log in with
     * @return if users credentials are correct // Return type---> "how" is the question!
     */
    public void logIn(String username, String password){
        serverConnection.checkLogin(username, password); // Return type of CheckLogin in serverConnection to be boolean??
                                                         // how can we verify is the question here
    }

    /**
     * Adds a user to the database
     * @param username username of user to add
     * @param password password user wants to use to log in
     */
    public void createUser(String username, String password){
        serverConnection.createUser(username, password); // How to handle duplicated username?
                                                         // This needs some sort of confirmation from server side as well

        // If serverConnection.createUser succeeds -> can set this username as an activeUser.
        // Or setActiveUser method can be called from server side directly using 'facade.setActiveUser' as well
        model.setActiveUser(username);
    }




}





