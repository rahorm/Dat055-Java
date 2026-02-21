package  Server;

import Other.Message;
import Other.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public final class DatabaseConnection {

    static final String DBNAME = "DAT055_ChatDB";
    static final String DATABASE = "jdbc:postgresql://localhost:5432/"+DBNAME;
    static final String USERNAME = "postgres";
    static final String PASSWORD = "Dia:23Postgres";

    private static DatabaseConnection instance;
    private Connection conn;

//-----------------Connection init-----------------//

    private DatabaseConnection() throws SQLException, ClassNotFoundException {
        this(DATABASE, USERNAME, PASSWORD);
    }

    private DatabaseConnection(String db, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        conn = DriverManager.getConnection(db, props);
    }

    public static DatabaseConnection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

//-----------------QUERIES-----------------//

    /**
     * Inserts a chatroom into the database
     * uses table Chats
     * Unique chatId is required
     * @param chatId used to identify chat
     * @param chatName name to display in app
     */
    public void createChatRoom(int chatId, String chatName){
        try(PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Chats VALUES (?, ?)");){
            ps.setInt(1,chatId);
            ps.setString(2,chatName);
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }

    }

    /**
     * !THIS CANNOT BE UNDONE!
     * Deletes a chatroom from the database and all related chat- and member-records
     *
     * @param chatId which chat to delete
     */
    public void deleteChatRoom(int chatId){
        try(PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM Chats WHERE chatId = ?");){
            ps.setInt(1, chatId);
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
    }

    /**
     * Checks if username already exists
     * @return if user already exists
     */
    public boolean checkUserExists(String username){
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT username FROM Users WHERE username = ?");){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * Checks if users login info is correct, meaning does
     * the users saved password match the one that was entered?
     * @param username what user to login
     * @param password password the user has entered
     * @return if the information is correct
     */
    public boolean checkLogIn(String username, String password){
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT pswd FROM Users WHERE username = ?");){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return (rs.getString(1).equals(password));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * Inserts a user into the database
     * uses table Users
     * Unique username is required
     * @param username username of user to create
     * @param password password linked to that user
     * @return returns the username of just created user
     */
    public String createUser(String username, String password){

        try(PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Users VALUES (?, ?)");){
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
        return username;
    }

    /**
     * Inserts a given message into the database
     * Uses table ChatMessages
     * Unique id required, user and chat must exist
     *
     * @param msg Message containing information to insert
     */
    public void sendMsg(Message msg){
        try(PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO ChatMessages VALUES (?, ?, ?, ?, ?, ?)");){
            ps.setInt(1, 1); //needs fixing
            ps.setInt(2, msg.getChatID());
            ps.setString(3, msg.getSender());
            ps.setTimestamp(4, Timestamp.valueOf(msg.getTimestamp().withNano(0)));
            ps.setString(5, msg.getContent());
            ps.setBoolean(6, false); //needs fixing
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
    }

    /**
     * !THIS CANNOT BE UNDONE!
     * Deletes a user from the database, removes them from any chats they were in and
     * deletes all messages sent by them.
     * @param username the user to delete
     */
    public void deleteUser(String username){
        try(PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM Users WHERE username = ?");){
            ps.setString(1, username);
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
    }

    /**
     * Adds a user to a chat
     * @param chatId which chat the user should be added to
     * @param username what user to add
     */
    public void addChatMember(int chatId, String username){
        try(PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO ChatMembers VALUES (?, ?);");){
            ps.setInt(1, chatId); //needs fixing
            ps.setString(2, username);
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
    }

    /**
     * !THIS CANNOT BE UNDONE!
     * Removes a user from specified chat and all messages sent by the user
     * @param chatId what chat to remove user from
     * @param username which user to remove
     */
    public void removeChatMember(int chatId, String username){
        try(PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM ChatMembers WHERE (chat = ? AND member = ?)");){
            ps.setInt(1, chatId);
            ps.setString(2, username);
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
    }

    /**
     * Edits an already existing message.
     * The message will have the same msgId and timestamp as before,
     * but the content and image properties may be changed
     * @param msg the original message
     * @param updateMsg the new message
     */
    public void editMsg(Message msg, Message updateMsg){

        try(PreparedStatement ps = conn.prepareStatement(
                "UPDATE ChatMessages SET Content = ?, hasimg = ? WHERE msgId = ?");){
            ps.setString(1,updateMsg.getContent());
            ps.setBoolean(2,updateMsg.getHasImg());
            ps.setInt(3, msg.getMessageID());
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }

    }

    /**
     * !THIS CANNOT BE UNDONE!
     * Deletes a message from a chat
     * @param msgId the message id of the message to be deleted
     */
    public void deleteMsg(int msgId){
        try(PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM ChatMessages WHERE msgId = ?");){
            ps.setInt(1, msgId);
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
    }


    /**
     * !THIS CANNOT BE UNDONE!
     * Deletes a message from a chat
     * @param msg the message to be deleted
     */
    public void deleteMsg(Message msg){
        try(PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM ChatMessages WHERE msgId = ?");){
            ps.setInt(1, msg.getMessageID());
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
    }

    /**
     * Fetches chatHistory, in ascending order by timestamp, from specified chat
     * @param chatId which chat to get history for
     * @return ArrayList<Message> returns an arraylist with Messages
     */
     public ArrayList<Message> getChatMessages(int chatId){
         ArrayList<Message> msgHistory = new ArrayList<Message>();

         try (PreparedStatement ps = conn.prepareStatement(
                 "SELECT * FROM chatmessages WHERE chat = ? ORDER BY time ASC;");){
             ps.setInt(1, chatId);
             ResultSet rs = ps.executeQuery();

             while(rs.next()) {

                 int msgId = rs.getInt(1);
                 int chat = rs.getInt(2);
                 String sender = rs.getString(3);
                 LocalDateTime time = rs.getTimestamp(4).toLocalDateTime();
                 String content = rs.getString(5);
                 boolean hasImg = rs.getBoolean(6);

                 msgHistory.add(new Message(msgId, sender, chat, content, time));

             }

         } catch (Exception e) {
             System.out.println(e.getMessage());
         }

         return msgHistory;
     }

    /**
     * Gets all members in a chat
     * @param chatId what chat to get members for
     * @return ArrayList<String> list of members
     */
    public ArrayList<String> getChatMembers(int chatId) {
        ArrayList<String> chatMembers = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT member FROM chatmembers WHERE chat = ?;");) {
            ps.setInt(1, chatId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                chatMembers.add(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chatMembers;
    }

    /**
     * Gets all chats available to the specified user
     * @param username what user to get chats for
     * @return an ArrayList of chatIds
     */
    public ArrayList<Integer> getAvailableChats(String username){
        ArrayList<Integer> availableChats = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT chat FROM chatmembers WHERE member = ?;");) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                availableChats.add(rs.getInt(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return availableChats;

    }


    // This is a hack to turn an SQLException into a JSON string error message. No need to change.
    public static String getError(SQLException e){
       String message = e.getMessage();
       int ix = message.indexOf('\n');
       if (ix > 0) message = message.substring(0, ix);
       message = message.replace("\"","\\\"");
       return message;
    }

    /*public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnection DBconn = DatabaseConnection.getInstance();
        Object obj = DBconn.checkLogIn("User1", "Pswd1");
        System.out.println("Output : " + obj);
    }*/
}