package  Server;

import Other.Message;

import java.sql.*;
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
   /*
    * createChatRoom(String chatName)
    * deleteChatRoom(int chatId)
    * boolean checkUserExists(String username)
    * checkLogIn(String username, String password)
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

    public void sendMsg(Message msg){
        try(PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO ChatMessages VALUES (?, ?, ?, ?, ?, ?)");){
            ps.setInt(1, 1); //needs fixing
            ps.setInt(2, msg.getChatID());
            ps.setTimestamp(4, Timestamp.valueOf(msg.getTimestamp()));
            ps.setString(5, msg.getContent());
            ps.setBoolean(6, false); //needs fixing
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
    }

    /*
     * deleteUser(String username)
     * addChatMember(int chatId, String username)
     * removeChatMember(int chatId, String username)
     * editMsg(Message msg, Message updateMsg)
     * deleteMsg(int msgId)/deleteMsg(Message msg)
     */

     public void getChatMessages(int chatId){
         try (PreparedStatement ps = conn.prepareStatement(
                 "SELECT * FROM chatmessages WHERE chat = ? ORDER BY time ASC;");){
             ps.setInt(1, chatId);
             ResultSet rs = ps.executeQuery();

             while(rs.next()) {

                 System.out.println("msgId: " + rs.getInt(1));
                 System.out.println("chat: " + rs.getInt(2));
                 System.out.println("sender: " + rs.getString(3));
                 System.out.println("time: " + rs.getTimestamp(4));
                 System.out.println("content: " + rs.getString(5));
                 System.out.println("hasImg: " + rs.getBoolean(6));
                 System.out.println("---------------");

             }

         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
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
        DBconn.getChatMessages(1);
    }
}