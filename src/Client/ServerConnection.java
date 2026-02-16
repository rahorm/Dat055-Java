package Client;

import Other.IdGenerator;
import Other.Message;
import Other.User;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.*;

public class ServerConnection {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private IdGenerator idGen = IdGenerator.getInstance();

    public ServerConnection(Socket socket){
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void connectionTest(){
        try {
            Scanner s = new Scanner(System.in);

            while(socket.isConnected()){
                int in = s.nextInt();

                bufferedWriter.write("client ping");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

// ------ Getters and setters ------------------------
    public Socket getSocket() {
    return socket;
}
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }
    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }
    public IdGenerator getIdGen() {
        return IdGenerator.getInstance();
    }
    public void setIdGen() {
        this.idGen = IdGenerator.getInstance();
    }

// -----Server related actions ----------------------------------
    /**
     * Creates a new chat in the database
     * <p>
     * The parameter chatName is the intended display name of the chat. The chat will recieve an id for internal use.
     * This method returns an int, 0, if no problems were encountered.
     *
     * @param chatName display name of chat
     * @return int message
     *
     * @throws SQLException
     */
    int createChatRoom(String chatName){

        return 0;
    }

    /**
     * Deletes a chat from the database
     * <p>
     * This method deletes the chat and all related information, such as members and messages from the database.
     * This cannot be undone.
     * This method returns an int, 0, if no problems were encountered.
     *
     * @param chatId id of chat to be deleted
     * @return int message
     *
     * @throws SQLException
     */
    int deleteChatRoom(int chatId){
        return 0;
    }

    /**
     * Creates a new user in the database
     * <p>
     * The parameter userName is the intended display name of the user. The user will recieve an id for internal use.
     * This method returns an int, 0, if no problems were encountered.
     *
     * @param userName display name of user
     * @return string returns the username
     *
     * @throws SQLException
     */
    String createUser(String userName){
        return "name";
    }

    /**
     * Deletes a user from the database
     * <p>
     * This method deletes the user and all related information, such messages sent, and removes the user from any chats
     * they were in from the database.
     * This cannot be undone.
     * This method returns an int, 0, if no problems were encountered.
     *
     * @param userId id of user to be deleted
     * @return int message
     *
     * @throws SQLException
     */
    int deleteUser(int userId){
        return 0;
    }
    int deleteUser(User user){
        return 0;
    }

    /**
     * Adds a user to the specified chat.
     * <p>
     * This method returns an int, 0, if no problems were encountered.
     *
     * @param chatId id of chat user should be added to
     * @param user the user that should be added
     * @return int message
     *
     * @throws SQLException
     */
    int addChatMember(int chatId, User user){
        return 0;
    }

    /**
     * Removes a user from the specified chat.
     * <p>
     * This method returns an int, 0, if no problems were encountered.
     *
     * @param chatId id of chat user should be added to
     * @param user the user that should be removed
     * @return int message
     *
     * @throws SQLException
     */
    int removeChatMember(int chatId, User user){
        return 0;
    }

    /**
     * Adds a message to the history of specified chat.
     *
     * @param msg the message to be sent
     * @return int message
     *
     * @throws SQLException
     */
    int sendMsg(Message msg){
        return 0;
    }

    /**
     * Replaces the previous message with updated message.
     * <p>
     * msg is the previous message which will be replaced. updatedMsg is what will be saved in its place.
     *
     * @param msg old message
     * @param updatedMsg updated message
     * @return int message
     *
     * @throws SQLException
     */
    int editMsg(Message msg, Message updatedMsg){
        return 0;
    }

    /**
     * Removes a message from the history of specified chat.
     *
     * @param msgId the message to be removed
     * @return int message
     *
     * @throws SQLException
     */
    int deleteMsg(int msgId){
        return 0;
    }
    int deleteMsg(Message msg){
        return 0;
    }

    /**
     * Gets chat history from specified chat, with the newest message last in the array.
     * ChatId specifies the id of the chat for which to get the history.
     *
     * @param chatId chat to get history for
     * @return Arraylist<Message> chatHistory
     *
     * @throws SQLException
     */
    ArrayList<Message> getChatMessages(int chatId){
        return new ArrayList<>();
    }

    /**
     * Gets members of specified chat.
     * ChatId specifies the id of the chat for which to get the members.
     *
     * @param chatId chat to get history for
     * @return Arraylist<User> members
     *
     * @throws SQLException
     */
    ArrayList<User> getChatMembers(int chatId){
        return new ArrayList<>();
    }

    /**
     * Gets the id's of all chats the specified user is a member in.
     *
     * @param user user to get chats for
     * @return Arraylist<int> chatId's
     *
     * @throws SQLException
     */
    ArrayList<Integer> getAvailableChats(User user){
        return new ArrayList<>();
    }

    //boolean checkLogIn(String username, String password){}
    //boolean checkUserExists(String username){}

}
