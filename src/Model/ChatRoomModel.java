package Model;

import Other.Message;

import java.util.ArrayList;

/**
 * The class represents the local state of a chatroom in'
 * the application
 * The model stores messages and members for one room
 * and extracts through getters and setters operations used by
 * Controller and Views
 */

public class ChatRoomModel {

    private ArrayList<Message> messages = new ArrayList<>();
    private final ArrayList<String> members = new ArrayList<>();
    private String activeUser;
   // private final ArrayList<User> members = new ArrayList<>();
   // private User activeUser;

    private final ArrayList<Integer> availableChatIds = new ArrayList<>();
    private final ArrayList<String> availableChatNames = new ArrayList<>();
    private String username;
    private int chatID;

    /**
     * This method creates a new chatroom model with chatID
     * and activeUser
     *
     * @param chatID
     * @param activeUser
     */
    public ChatRoomModel(int chatID, String activeUser) {
        if (activeUser == null || chatID <= 0) {
            throw new IllegalArgumentException("This chat room can not be created");
        }

        this.chatID = chatID;
        this.activeUser = activeUser;

    }



    /// ----------------------------- Message -----------------------------
    public ArrayList<Message> retriveMSGList() {
        /**
         * This method retrieves all messages from
         * currently stored in MSGList for the room
         * @param none
         * @return list of messages
         */
        return new ArrayList<>(messages);
    }

    public void setHistory(ArrayList<Message> history){ this.messages = history;}
    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage(){
        messages.clear();
    }

    /// ----------------------------- USER -----------------------------
    public ArrayList<String> retrieveUserList() {
        /**
         * This method retrieves all User from
         * currently stored in UserList for the room
         * @param none
         * @return list of Users
         */
        return new ArrayList<>(members);
    }

    public void addUser(String user) {
        members.add(user);
    }

    public void removeUser(String user) {
        members.remove(user);
    }

    public void setActiveUser(String activeUser) {

        this.activeUser = activeUser;  //  Lägg till / uppdatera
    }

    public String getActiveUser(){

        return this.activeUser;
    }

    public void setUsername(String username){
        this.username = username;
    }

    /// ----------------------------- ChatRoom-----------------------------
    public int getChatID() {

        return chatID;
    }

    public void changeActiveRoom(int chatID) {
        this.chatID = chatID;
    }

    /*public void removeChatRoom(int chatID) { // I don't know how to remove an existing model!!!! - choi
        System.out.println("model wants to remove chatroom right now");
    }*/


    public void addAvailableChat(int chatId, String chatName) {
        availableChatIds.add(chatId);
        availableChatNames.add(chatName);
    }
}



