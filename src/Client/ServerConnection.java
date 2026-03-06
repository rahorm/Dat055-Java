package Client;

import Common.*;
//import Common.SendMsgWrapper;
import Other.Message;
import Other.PictureMessage;

import java.net.Socket;
import java.sql.*;
import java.util.*;


public class ServerConnection {

    private ServerHandler serverHandler;

    public ServerConnection(Socket socket) {
            this.serverHandler = new ServerHandler(socket);
            Thread thread = new Thread(serverHandler);  //begins listening to the thread. When input is heard serverHanlers run() is invoked
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
    public void sendPictureMsg(PictureMessage pictureMessage) {
        serverHandler.broadcastMessage(new RequestWrapper(RequestType.ADD_MESSAGE, pictureMessage));
    }

    /**
     * Removes a message from the history of the chat it belongs to.
     * </p>
     * @param msg - the message in type Message to be removed
     */
    public void deleteMsg(Message msg){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.DELETE_MESSAGE, msg));
    }

    /**
     * Gets chat history from specified chat, with the newest message last in the array.
     * </p>
     * @param chatId int value representing chat to get history from
     * @return Arraylist with Message type value
     * @todo question: does this return messages right now or an empty arraylist.
     */
    public void getChatMessages(int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.GET_MESSAGES, chatId));
    }

//------------------------------------Chats med nya Wrappers----------------------------
    /**
     * Adds a new chat in the database
     * </p>
     * // The parameter chatName is the intended display name of the chat. The chat will recieve an id for internal use.
     * @todo fix javadoc
     * @param chatId int name of chat
     */
    public void createChatRoom(int chatId, String chatName){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.ADD_CHATROOM, new ChatData(chatId, chatName)));
    }


    /**
     * This method deletes the chat and all related information, such as members and messages from the database.
     * This cannot be undone.
     * </p>
     * @param chatId int of id of chat to be deleted
     */
    public void deleteChatRoom(int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.DELETE_CHATROOM, chatId));
    }

//---------------------------------User med nya wrappers-----------------------------

    /**
     * Outputs true if given username corresponds to user in database
     *
     * @param username String ID of user
     * @todo double check that it should be string here
     * @return boolean representing if the given user exists in database
     * */
    public boolean checkUserExists(String username){
        serverHandler.broadcastMessage(new RequestWrapper(RequestType.CHECK_USER, username));
        return true;
    }

    /**
     * Check with the database if login is correct. Easy but unsafe implementation.
     *
     * @param username String representing the ID of a user
     * @param password String of the password
     * */
    public void login(String username, String password){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.LOGIN, new UserData(username, password)));
    }

    /**
     * Creates a new user in the database. Easy but unsafe password handling.
     * </p>
     *
     * @param userName string parameter userName is the intended display name of the user.
     * @param password String
     */
    public void createUser(String userName, String password){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.CREATE_USER, new UserData(userName, password)));
    }

    /**
     * Deletes a user from the database
     * </p>
     * This method deletes the user and all related information, such messages sent, and removes the user from any chats
     * they were in from the database.
     * This cannot be undone.
     *
     * @param username string id of user to be deleted
     */

    public void deleteUser(String username){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.DELETE_USER, username));
    }

    //---------------------------------Chat med nya wrappers---------------------------------
    /**
     * Adds a user to the activeChat
     * </p>
     * @param user username of user that should be added.
     * @param chatId id of chat to add user to
     */

    public void addMember(String user, int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.ADD_MEMBER, new ChatMemberData(user, chatId)));
    }

    /**
     * Removes a user from the activeChat
     * </p>
     * @param user the user that should be removed. Type String.
     */
    public void removeChatMember(String user, int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.REMOVE_CHAT_MEMBER, new ChatMemberData(user, chatId)));
    }

    /**
     * Gets members of specified chat.
     * ChatId specifies the id of the chat for which to get the members.
     * </p>
     * @param chatId chat to get history for
     * @return Arraylist<User> members @todo ?? see next todo
     *
     * @throws SQLException
     */
    public ArrayList<String> getChatMembers(int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.GET_CHAT_MEMBERS, chatId));
        return new ArrayList<>(); // @todo what happens here? what will be in this arraylist?
    }

    /**
     * Gets the id's of all chats the specified user is a member in.
     * </p>
     * @param user user to get chats for
     * @return Arraylist<int> chatId's
     *
     * @throws SQLException
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


/*----------------------GAMLA ANTECKNINGAR---------------------------------
    //boolean checkLogIn(String username, String password){}
    //boolean checkUserExists(String username){}

    /**
     * Replaces the previous message with updated message.
     *
     * msg is the previous message which will be replaced. updatedMsg is what will be saved in its place.
     *
     * @param msg old message
     * @param updatedMsg updated message
     * @return int message
     *
     * @throws SQLException
     */
   /*public int editMsg(Message msg, Message updatedMsg){
        return 0;
    }
*/