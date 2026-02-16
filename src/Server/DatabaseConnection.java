package  Server;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

    static final String DBNAME = "DAT055_ChatDB";
    static final String DATABASE = "jdbc:postgresql://localhost:5432/"+DBNAME;
    static final String USERNAME = "postgres";
    static final String PASSWORD = "Dia:23Postgres";

    private Connection conn;

//-----------------Connection init-----------------//

    public DatabaseConnection() throws SQLException, ClassNotFoundException {
        this(DATABASE, USERNAME, PASSWORD);
    }

    public DatabaseConnection(String db, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        conn = DriverManager.getConnection(db, props);
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

    /*
     * deleteUser(String username)
     * addChatMember(int chatId, String username)
     * removeChatMember(int chatId, String username)
     * sendMsg(Message msg)
     * editMsg(Message msg, Message updateMsg)
     * deleteMsg(int msgId)/deleteMsg(Message msg)
     * getChatMessages(int chatId)
     * getChatMembers(int chatId)
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

    /*public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnection DBconn = new DatabaseConnection();
        System.out.println(DBconn.createUser("user4", "pswd4"));
    }*/
}