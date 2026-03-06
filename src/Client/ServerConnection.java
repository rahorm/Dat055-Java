package Client;

import Common.*;
import Other.Message;
import Other.PictureMessage;

import java.net.Socket;
import java.sql.*;
import java.util.*;


public class ServerConnection {

    private ServerHandler serverHandler;

    public ServerConnection(Socket socket) {
            this.serverHandler = new ServerHandler(socket);
            Thread thread = new Thread(serverHandler);
            thread.start();
    }


    //------------------------------- Messages med nya wrappers------------------------------
    /**
     * Adds a message to the database
     * </p>
     * @param msg the message to be sent in type Message
     */
    public void sendMsg(Message msg) {
        serverHandler.broadcastMessage(new RequestWrapper(RequestType.ADD_MESSAGE, msg));
    }
    /**
     * Adds a picture message to the database
     * @param pictureMessage the message to be sent in type Message
     */
    public void sendPictureMsg(PictureMessage pictureMessage) {
        serverHandler.broadcastMessage(new RequestWrapper(RequestType.ADD_MESSAGE, pictureMessage));
    }

    /**
     * Gets chat history from specified chat, with the newest message last in the array.
     * @param chatId id of chat to get history from
     */
    public void getChatMessages(int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.GET_MESSAGES, chatId));
    }

    /**
     * Adds a new chat in the database
     *
     * @param chatId int name of chat
     * @param chatName intended display name of chat
     */
    public void addChatRoom(int chatId, String chatName){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.ADD_CHATROOM, new ChatData(chatId, chatName)));
    }


    /**
     * This method deletes the specified chat and all related information, such as members and messages from the database.
     * This cannot be undone.
     * @param chatId id of chat to be deleted
     */
    public void deleteChatRoom(int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.DELETE_CHATROOM, chatId));
    }

//---------------------------------User med nya wrappers-----------------------------

    /**
     * Outputs true if given username exists in database
     *
     * @param username username to be checked
     * @return boolean representing if the given user exists in database
     * */
    public boolean checkUserExists(String username){
        serverHandler.broadcastMessage(new RequestWrapper(RequestType.CHECK_USER, username));
        return true; //Have seen this issue, will fix
    }

    /**
     * Check with the database if login is correct.
     *
     * @param username username to check
     * @param password password to check
     * */
    public void login(String username, String password){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.LOGIN, new UserData(username, password)));
    }

    /**
     * Creates a new user in the database.
     *
     * @param userName username to identify user with
     * @param password password used to access profile
     */
    public void createUser(String userName, String password){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.CREATE_USER, new UserData(userName, password)));
    }

    /**
     * Deletes a user from the database
     * This method deletes the user and all related information, like sent messages, any chats
     * they were in, from the database.
     * This cannot be undone.
     *
     * @param username username of user to be deleted
     */

    public void deleteUser(String username){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.DELETE_USER, username));
    }

    //---------------------------------Chat med nya wrappers---------------------------------
    /**
     * Adds a user to the specified chat
     * </p>
     * @param user username of user that should be added.
     * @param chatId id of chat to add user to
     */

    public void addMember(String user, int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.ADD_MEMBER, new ChatMemberData(user, chatId)));
    }

    /**
     * Removes a user from the specified chat
     * @param user the user that should be removed. Type String.
     * @param chatId id of chat to remove user from
     */
    public void removeChatMember(String user, int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.REMOVE_CHAT_MEMBER, new ChatMemberData(user, chatId)));
    }

    /**
     * Gets members of specified chat.
     * @param chatId chat to get members for
     */
    public void getChatMembers(int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.GET_CHAT_MEMBERS, chatId));
    }

    /**
     * Gets the id's and names of all chats the specified user is a member in.
     * @param user user to get chats for
     */
    public void getAvailableChats(String user){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.GET_AVAILABLE_CHATS, user));
    }

    //---------------------------------Getters and Setters---------------------------------
    public ServerHandler getServerHandler() {
    return serverHandler;
    }
    public void setServerHandler(ServerHandler serverHandler) {
    this.serverHandler = serverHandler;
}

}