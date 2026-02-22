package Client;

import Common.RequestType;
import Common.RequestWrapper;
import Other.Message;
import java.net.Socket;
import java.sql.*;
import java.util.*;
// import Common.SendMsgWrapper;

public class ServerConnection {

    private ServerHandler serverHandler;

    public ServerConnection(Socket socket) {
            this.serverHandler = new ServerHandler(socket);
            Thread thread = new Thread(serverHandler);
            thread.start();
    }


    /**
     * Adds a message to the database
     * </p>
     * @param msg the message to be sent in type Message
     */
    public void sendMsg(Message msg){
        serverHandler.broadcastMessage(new RequestWrapper(RequestType.ADD_MESSAGE, msg));
    }

    // IS NOT IMPLEMENTED LIKE THE JAVADOC YET DUE TO SIMPLISITY
    /**
     * Adds a new chat in the database
     * </p>
     * // The parameter chatName is the intended display name of the chat. The chat will recieve an id for internal use.
     * // This method returns an int, 0, if no problems were encountered.
     *
     * @param chat int id of chat
     */
    public void createChatRoom(int chat){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.ADD_CHATROOM, chat)
        );
    }

    /**
     * Deletes a chat from the database
     * </p>
     * This method deletes the chat and all related information, such as members and messages from the database.
     * This cannot be undone.
     *
     * @param chat int of id of chat to be deleted
     */
    public void deleteChatRoom(int chat){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.DELETE_CHATROOM, chat)
        );
    }

// unsure if display names are implemented yet
    /**
     * Creates a new user in the database
     * </p>
     * The parameter userName is the intended display name of the user. The user will recieve an id for internal use.
     * This method returns an int, 0, if no problems were encountered.
     *
     * @param user String id of user
     */
    public void createUser(String user){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.ADD_USER, user)
        );
    }

    /**
     * Deletes a user from the database
     * </p>
     * This method deletes the user and all related information, such messages sent, and removes the user from any chats
     * they were in from the database.
     * This cannot be undone.
     * This method returns an int, 0, if no problems were encountered.
     *
     * @param user int id of user to be deleted
     */
    public void deleteUser(int user){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.DELETE_USER, user)
        );
    }

    /**
     * Adds a user to the activeChat
     * </p>
     * @param user the user that should be added. Type String
     */
    public void addChatMember(String user){

        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.ADD_CHAT_MEMBER, user)
        );
    }

    /**
     * Removes a user from the activeChat
     * </p>
     * @param user the user that should be removed. Type String.
     */
    public void removeChatMember(String user){

        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.REMOVE_CHAT_MEMBER, user)
        );
    }

//    /**
//     * Replaces the previous message with updated message.
//     *
//     * msg is the previous message which will be replaced. updatedMsg is what will be saved in its place.
//     *
//     * @param msg old message
//     * @param updatedMsg updated message
//     * @return int message
//     *
//     * @throws SQLException
//     */
//    public int editMsg(Message msg, Message updatedMsg){
//        return 0;
//    }

    /**
     * Removes a message from the history of specified chat.
     * </p>
     * @param msg the message to be removed
     */
    public void deleteMsg(Message msg){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.DELETE_MESSAGE, msg)
        );
    }

    // PROB WRONG
    /**
     * Gets chat history from specified chat, with the newest message last in the array.
     * ChatId specifies the id of the chat for which to get the history.
     * </p>
     * @param chatId chat to get history for
     * @return Arraylist<Message> chatHistory
     */
    public ArrayList<Message> getChatMessages(int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.GET_MESSAGES, chatId)
        );
        return new ArrayList<>();
    }


    // PROB WRONG
    /**
     * Gets members of specified chat.
     * ChatId specifies the id of the chat for which to get the members.
     * </p>
     * @param chatId chat to get history for
     * @return Arraylist<User> members
     *
     * @throws SQLException
     */
    public ArrayList<String> getChatMembers(int chatId){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.GET_CHAT_MEMBERS, chatId)
        );
        return new ArrayList<>();
    }

    //PROB WRONG
    /**
     * Gets the id's of all chats the specified user is a member in.
     * </p>
     * @param user user to get chats for
     * @return Arraylist<int> chatId's
     *
     * @throws SQLException
     */
    public ArrayList<Integer> getAvailableChats(String user){
        serverHandler.broadcastMessage(
                new RequestWrapper(RequestType.GET_AVAILABLE_CHATS, user)
        );
        return new ArrayList<>();
    }

    //boolean checkLogIn(String username, String password){}
    //boolean checkUserExists(String username){}



    public ServerHandler getServerHandler() {
        return serverHandler;
    }
    public void setServerHandler(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }
}

