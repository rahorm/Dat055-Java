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

        this.activeChatRoomId = chatID;
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

    public void setMembers(ArrayList<String> members){
        this.members = members;
    }
    public void addUser(String user) {
        members.add(user);
    }

    public void removeUser(String user) {
        members.remove(user);
    }

    public void setActiveUser(String activeUser) {

        this.activeUser = activeUser;
    }

    public String getActiveUser(){

        return this.activeUser;
    }

    public void setUsername(String username){
        this.username = username;
    }

    /// ----------------------------- ChatRoom-----------------------------
    public int getActiveChatRoomId() {
        return activeChatRoomId;
    }
    public ArrayList<Integer> getAvailableChatIds() {
        return availableChatIds;
    }
    public ArrayList<String> getAvailableChatNames() {
        return availableChatNames;
    }

    //Namn för specifik chatId
    public String getChatNameForId(int chatId) {
        int index = availableChatIds.indexOf(chatId);
        return index >= 0 ? availableChatNames.get(index) : "Unknown (" + chatId + ")";
    }

    public void changeActiveRoom(int chatID) {
        this.activeChatRoomId = chatID;
    }

    /*public void removeChatRoom(int chatID) { // I don't know how to remove an existing model!!!! - choi
        System.out.println("model wants to remove chatroom right now");
    }*/

    // Sätt ny lista/uppdatera listan
    public void setAvailableChats(ArrayList<ChatData> idNamePairs) {

        for (ChatData d : idNamePairs) {
            availableChatIds.add(d.getChatId());
            availableChatNames.add(d.getChatName());
        }
    }
    public void clearAvailableChats(int chatId, String chatName) {
        availableChatIds.clear();
        availableChatNames.clear();
    }

    public void addAvailableChat(int chatId, String chatName) {
        availableChatIds.add(chatId);
        availableChatNames.add(chatName);
    }

}



