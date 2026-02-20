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
     * @param chatId
     * @param chatName
     * @return
     */
    public void createChatRoom(int chatId, String chatName){}
    public void deleteChatRoom(int chatId){}
    public boolean checkUserExists(String username){
        return true;
    }
    public boolean checkLogIn(String username, String password){
        return true;
    }

    /**
     * Inserts a user into the database
     * uses table Users
     * Unique username is required
     * @param username username of user to create
     * @param password password linked to that user
     * @return
     */
    public String createUser(String username, String password){

        try(PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Users VALUES (?, ?)");){
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
        }  catch (SQLException e) {
            return "{\"success\":false, \"error\":\""+getError(e)+"\"}";
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


    public void deleteUser(String username){}
    public void addChatMember(int chatId, String username){}
    public void removeChatMember(int chatId, String username){}
    public void editMsg(Message msg, Message updateMsg){}
    public void deleteMsg(int msgId){}
    public void deleteMsg(Message msg){}

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

    /* getChatMembers(int chatId)
     * getAvailableChats(String username)
     */


    // This is a hack to turn an SQLException into a JSON string error message. No need to change.
    public static String getError(SQLException e){
       String message = e.getMessage();
       int ix = message.indexOf('\n');
       if (ix > 0) message = message.substring(0, ix);
       message = message.replace("\"","\\\"");
       return message;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnection DBconn = DatabaseConnection.getInstance();
        ArrayList<Message> msg = DBconn.getChatMessages(1);
        System.out.println("ArrayList : " + msg);
    }
}