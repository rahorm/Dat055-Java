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

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    /**
     * Adds a user to the member list of this chat room.
     * If the user is already a member, nothing
     *
     * @param user user to add; must not be  null
     * @throws NullPointerException if user is null
     */
    public void addMember(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }
        if (!members.contains(user)) {
            members.add(user);
        }
    }

    /**
     * Adds a user to the member list of this chat room.
     * If the user is already a member, nothing
     *
     * @param user user to add; must not be null
     * @throws NullPointerException if  user is null
     */
    public void addMember(User user) {

        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }
        if (!members.contains(user)) {
            members.add(user);
        }
    }

    /**
     * Removes a user from the member list of this chat room.
     * If the user is not a member, nothing happens.
     *
     * @param user user to remove; must not be  null
     * @throws NullPointerException if  user is null
     */
    public void removeMember(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }
        members.remove(user);
    }

    /**
     * Adds a new message to this chat room.
     *
     * @param message message to add; must not be {@code null}
     * @throws NullPointerException if {@code message} is {@code null}
     */
    public void addMessage(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("message must not be null");
        }
        messages.add(message);
    }

    /**
     * Removes a message from this chat room.
     * If the message is not found, nothing happens.
     *
     * @param message message to remove; must not be null
     * @throws NullPointerException if message is null
     */
    public void removeMessage(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("message must not be null");
        }
        messages.remove(message);
    }
}

