package Controller;

import java.time.LocalDateTime;

/**
 * Notes:
 * - Change UML name to controller
 * - where is all the dif chatrooms stored?, method 1 needs it maybe?
 * - should msgs and user params be of type user and message?
 * - i think numberformatexception is wrong
 * */
public class Controller {

    /**
     * Takes an integer representing an existing chatRoom ID and makes that the active chatRoom
     * @param chatID integer representing a chatRoom
     * @throws IllegalArgumentException if chatID isn't an existing ID for a chatRoom
     * */
    public void changeActiveRoom(int chatID){

    }

    /**
     * Takes message and stores it in the program
     * @param msg string that represents a message that has been input from a user
     * @param userID integer value that represents a user in the active chat that is the sender of the message
     * @throws IllegalArgumentException if userID is not an existing user
     * @throws NumberFormatException if msg isn't a string
     * */
    public void sendMessage(String msg, int userID, LocalDateTime timeStamp){

    }
    /**
     * Removes the chatRoom, you can not remove the activeChatRoom
     * @param chatID integer that represents a chatRoom
     * @throws IllegalArgumentException if chatID does not represent a chatRoom that exists
     * @throws IllegalArgumentException if the chatID given is the activeChatRoom
     * */
    public void removeChatRoom(int chatID){

    }

    /**
     * Creates a new ChatRoom and makes that the activeChatRoom
     * @throws IllegalStateException if the max amount of chatRooms have been reached
     * */
    public void addChatRoom(){

    }



}
