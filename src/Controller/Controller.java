package Controller;

import Model.ChatRoomFacade;
import Other.PictureMessage;

import java.io.File;

public class Controller {

    private final ChatRoomFacade facade;

    public Controller(ChatRoomFacade chatFacade) {
        this.facade = chatFacade;
    }

    /**
     * Takes an integer representing an existing chatRoom ID and makes that the active chatRoom
     * @param chatID integer representing a chatRoom
     * @throws IllegalArgumentException if chatID isn't an existing ID for a chatRoom
     * */
    public void changeActiveRoom(int chatID) {
        if (chatID < 0) {
            throw new IllegalArgumentException("chatID must be positive");
        }
        facade.changeActiveRoom(chatID);
    }

    /**
     * Takes message and stores it in the model
     * @param msg string that contains the message that should be stored
     * @throws IllegalArgumentException if message is null or empty
     */
    public void sendMessage(String msg){

        if (msg == null || msg.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        facade.storeMsg(msg);
    }

    /**
     * Takes a picture message and stores it in the program
     * @param imagePath string that represents the local file path of the image to be sent
     * @param message string that contains the message that should be stored
     * @throws IllegalArgumentException if imagePath or user is null/empty
     * @throws IllegalArgumentException if message is null
     * @throws IllegalArgumentException if the file at imagePath does not exist
     */
    public void sendPictureMessage(String imagePath, String message) {

        if (imagePath == null || imagePath.isEmpty()) {
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        File imageFile = new File(imagePath);
        if (!imageFile.exists() || !imageFile.isFile()) {
            throw new IllegalArgumentException("No file found at path: " + imagePath);
        }

        PictureMessage pictureMessage = new PictureMessage(facade.getActiveUser(), facade.getActiveChatRoom(), imagePath, message);

        facade.storePictureMsg(pictureMessage);

    }

    /**
     * Removes the specified chatRoom, including all messages, you can not remove the activeChatRoom
     * @param chatID id of chatRoom to be removed
     * @throws IllegalArgumentException if chatID does not represent a chatRoom that can exist
     * */
    public void removeChatRoom(int chatID) {
        if (chatID < 0) {
            throw new IllegalArgumentException("Invalid chatID");
        }

        facade.removeChatRoom(chatID);
    }

    /**
     * Creates a new ChatRoom and makes that the activeChatRoom
     * @throws IllegalStateException if the max amount of chatRooms have been reached
     * */
    public void addChatRoom(String chatName) {
        facade.addChatRoom(chatName);
    }

    /**
     * changes active user to the specified user
     *
     * @param user username of user
     * */

    public void changeActiveUser(String user) {
        facade.setActiveUser(user);
    }


    public void login(String user, String password){
        facade.logIn(user, password);
    }

    public void addMember(String user){
        facade.addMember(user);
    }
    public void addMember(String user, int chatID){
        facade.addMember(user, chatID);
    }

    /**
     * Adds a new user to the database
     *
     * @param user will be the users displayed name and identifier in the system
     * @throws IllegalArgumentException if username is null or empty
     * */
    public void signUp(String user, String password) {
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }
        facade.createUser(user, password);
    }

    public int getChatIdByName(String name) {
        return facade.getChatIdByName(name);
    }
}
