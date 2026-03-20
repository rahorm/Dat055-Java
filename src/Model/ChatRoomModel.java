package Model;

import Common.ChatData;
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
    private ArrayList<String> members = new ArrayList<>();
    private String activeUser;

    private final ArrayList<Integer> availableChatIds = new ArrayList<>();
    private final ArrayList<String> availableChatNames = new ArrayList<>();
    private String username;
    private int activeChatRoomId;
    private String statusMessage;

    /**
     * Creates a new chatroom model with chatID
     * and activeUser
     *
     * @param chatID
     * @param activeUser
     */
    public ChatRoomModel(int chatID, String activeUser) {
        if (activeUser == null || chatID <= 0) {
            throw new IllegalArgumentException("This chat room can not be created");
        }

        this.activeChatRoomId = chatID;
        this.activeUser = activeUser;
    }

    /// ----------------------------- Message -----------------------------

    /**
     * Returns the Message list in the model.
     * The provided list contains Message objects that represent messages
     */
    public ArrayList<Message> getMessages() { return messages; }

    /**
     * Sets the history list of chats in the model.
     * @param history
     * The provided list contains Message objects that represent messages
     */
    public void setHistory(ArrayList<Message> history){ this.messages = history;}


    /**
     * Adds a new message to this chat room.
     * @param message message to add.
     */
    public void addMessage(Message message) {
        messages.add(message);
    }


    /**
     * Removes message from active chatRoom.
     */
    public void removeMessage(){
        messages.clear();
    }

    /// ----------------------------- USER -----------------------------
    /**
     * Removes a user from the member list of this chat room.
     * @param user to remove.
     */
    public void removeUser(String user) {
        members.remove(user);
    }

    /**
     * Returns member list of this chat room after a database update has happened
     */
    public ArrayList<String> getMembers() { return members; }

    /**
     * Sets the current chatRooms memberlist after a database update has happened
     */
    public void setMembers(ArrayList<String> members){ this.members = members; }


    /**
     * Adds a user to the member list of this chat room after a database update has happened
     */
    public void addUser(String user) {
        members.add(user);
    }


    /**
     * Sets activeUser in the model
     * @param activeUser that is the currently logged in user
     */
    public void setActiveUser(String activeUser) { this.activeUser = activeUser; }

    /**
     * Gets activeUser, ie the logged in user from the model
     */
    public String getActiveUser(){ return activeUser; }


    /**
     * Sets username of user trying to log in
     */
    public void setUsername(String username){
        this.username = username;
    }

    /// ----------------------------- ChatRoom-----------------------------

    /**
     * Returns the current activeChatRoom by its Id
     */
    public int getActiveChatRoomId() {
        return activeChatRoomId;
    }


    /**
     * Returns availableChatIds for current logged in user
     */
    public ArrayList<Integer> getAvailableChatIds() {
        return availableChatIds;
    }


    /**
     * Returns availableChatNames for current logged in user
     */
    public ArrayList<String> getAvailableChatNames() {
        return availableChatNames;
    }



    /**
     * Returns ChatName for current logged in user by
     * given chatId for the chatRoom
     * @param chatId
     */
    public String getChatNameForId(int chatId) {
        int index = availableChatIds.indexOf(chatId);
        return index >= 0 ? availableChatNames.get(index) : "Unknown (" + chatId + ")";
    }


    /**
     * User wants to change an active room, the chatroom that user wants to
     * open will be identified by chatID, this chatID should be sent as a parameter
     * when it's called in Control.
     * @param chatID of a chatroom that user wants to open
     */
    public void setActiveRoom(int chatID) { this.activeChatRoomId = chatID; }


    /**
     * Updates the list of available chats in the model
     * @param idNamePairs
     * The provided list contains ChatData objects that represent chat
     * ID–name pairs, which will replace the current available chats in the model.
     */
    public void setAvailableChats(ArrayList<ChatData> idNamePairs) {

        availableChatIds.clear();
        availableChatNames.clear();
        for (ChatData d : idNamePairs) {
            availableChatIds.add(d.getChatId());
            availableChatNames.add(d.getChatName());
        }
    }

    /**
     * Clears a chat from being available to a user in the model by clearing
     * that chatName and chatId from availableChats lists
     * @param chatId
     * @param chatName
     */
    public void clearAvailableChats(int chatId, String chatName) {
        availableChatIds.clear();
        availableChatNames.clear();
    }
    /**
     * Adds a chat in the available list to a user by adding
     * that chatName and chatId to availableChats lists
     * @param chatId
     * @param chatName
     */
    public void addAvailableChat(int chatId, String chatName) {
        availableChatIds.add(chatId);
        availableChatNames.add(chatName);
    }

    /**
     * When user tries to login or sign up this statusmessage will shows whether it is failed or succeeded
     * @param statusmessage message that indicates signup or login status
     */
    public void setStatusMessage(String statusmessage){
        this.statusMessage = statusmessage;
    }


    /**
     * When user tries to login or sign up this statusmessage will shows whether it is failed or succeeded
     * @returns statusMessage
     */
    public String getStatusMessage(){
        return this.statusMessage;
    }


    /**
     * Returns ChatId for current logged in user by
     * given chatname for the chatRoom
     * @param name
     */
    public int getChatIdByName(String name) {
        int index = availableChatNames.indexOf(name);
        if (index != -1) {
            return availableChatIds.get(index);
        }
        return -1;
    }

}




