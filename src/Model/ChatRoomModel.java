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

    private final ArrayList <Message> messages = new ArrayList<>();
    private final ArrayList <User> members = new ArrayList<>();
    private User activeUser;
    private final int chatID;

    /**
     * This method creates a new chatroom model with chatID
     * and activeUser
     * @param chatID
     * @param activeUser
     */
    public ChatRoomModel(int chatID, User activeUser){
        this.chatID =chatID;
        this.activeUser= activeUser;

    }

    /**
     *
     * @return
     */

    public ArrayList<Message> retriveMSGList(){
        /**
         * This method retrieves all messages from
         * currently stored in MSGList for the room
         * @param none
         * @return list of messages
         */
        return new ArrayList<>(messages);
    }


    public int getChatID(){
        return chatID;
    }

    /*
    *-> Ändra UserID usage till username
    1)Get activeuser - public User getActiveUser()
    * 2)Set -||-
    *
    * 3)public void removeMember (User user){
    * if (user== null){
    * throw new IllegalArgumentException(?)("User must exist");
    * }
    * members.remove(user);
    * }
    *
    * 4)-||- addMember -- if member not null AND !members.contains(user)
    * --> members.add(user);
    *
    * 5) public void addMessage (Message m){
    * messages.add(m);
    * }
    *
    * 6) public void removeMessage -||-
    *
    * */
}