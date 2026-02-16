package Model;

import Other.Message;
import Other.User;

import java.util.Observable;

public class ChatRoomFacade extends Observable {
    private ChatRoomModel model;


    public ChatRoomFacade(ChatRoomModel model) {
        this.model = model;
    }

    /**
     * User wants to change an active room, the chatroom that user wants to
     * open will be identified by chatID, this chatID should be sent as a parameter
     * when it's called in Control.
     *
     * @param chatID of a chatroom that user wants to open
     */
    public void changeActiveRoom(int chatID) {
        //model.changeActiveRoom(chatID);

        setChanged(); // Apparently it should be used before using notifyObservers

        //notifyObservers(Object o);
    }

		 /* Not needed now but can be useful to have/know
		 public ChatRoomModel getState(){
			 return this.model;
    } */


/**
 * Adds a user to the member list of this chat room.
 * If the user is already a member, nothing
 *
 * @param user user to add; must not be  null
 * @throws IllegalArgumentException if user is null
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
 * @throws IllegalArgumentException if  user is null
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
 * @param message message to add; must not be null
 * @throws NullPointerException if  message is  null
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
 * @throws IllegalArgumentException if message is null
 */
public void removeMessage(Message message) {
    if (message == null) {
        throw new IllegalArgumentException("message must not be null");
    }
    messages.remove(message);
}
}