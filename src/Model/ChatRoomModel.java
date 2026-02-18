package Model;

import Other.Message;
import Other.User;

import java.util.ArrayList;
import java.util.Observable;

/**
 * The class represents the local state of a chatroom in'
 * the application
 * The model stores messages and members for one room
 * and extracts through getters and setters operations used by
 * Controller and Views
 */

public class ChatRoomModel {

    private final ArrayList<Message> messages = new ArrayList<>();
    private final ArrayList<User> members = new ArrayList<>();
    private User activeUser;
    private int chatID;

    /**
     * This method creates a new chatroom model with chatID
     * and activeUser
     *
     * @param chatID
     * @param activeUser
     */
    public ChatRoomModel(int chatID, User activeUser) {
        this.chatID = chatID;
        this.activeUser = activeUser;

    }

    public ArrayList<Message> retriveMSGList() {
        /**
         * This method retrieves all messages from
         * currently stored in MSGList for the room
         * @param none
         * @return list of messages
         */
        return new ArrayList<>(messages);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public ArrayList<User> retrieveUserList() {
        /**
         * This method retrieves all User from
         * currently stored in UserList for the room
         * @param none
         * @return list of Users
         */
        return new ArrayList<>(members);
    }

    public void addUser(User user) {
        members.add(user);
    }

    // BLAH BLAH

    public int getChatID() {

        return chatID;
    }

    public void setActiveUser(User activeUser) {

            this.activeUser = activeUser;  //  Lägg till / uppdatera
    }

    public User getActiveUser(){

        return this.activeUser;
    }

    /*
    public void setActiveUser(String username) {

            User currentuser = new User(username)
            this.activeUser = currentuser;
    }
    */

}



