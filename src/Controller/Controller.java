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
     * Takes message and stores it in the program
     * @param msg string that represents a message that has been input from a user
     * @throws IllegalArgumentException if msg is empty
     * */
    public void sendMessage(String msg){
        if (msg == null || msg.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        facade.storeMsg(msg);

    }

    /**
     * Takes a picture message and stores it in the program
     * @param imagePath string that represents the local file path of the image to be sent
     * @throws IllegalArgumentException if imagePath is null/empty
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
     * Removes the chatRoom, you can not remove the activeChatRoom
     * @param chatID integer that represents a chatRoom
     * @throws IllegalArgumentException if chatID does not represent a chatRoom that exists
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
     * sets the active user to the input id
     * @param user String that represents id of a user in the system
     * */

    public void changeActiveUser(String user) {
        facade.setActiveUser(user);

    }

    /**
     * login function using the written user and password
     * @param user String that represents id of a user in the system
     * */
    public void login(String user, String password){
        facade.logIn(user, password);
    }

    /**
     * add user to the activeChatRoom
     * @param user String that represents id of a user in the system
     * */
    public void addMember(String user){
        facade.addMember(user);
    }

    /**
     * add user to a given ChatRoom
     * @param user String that represents id of a user in the system
     * @param chatID that represents the ChatRoom
     * */
    public void addMember(String user, int chatID){
        facade.addMember(user, chatID);
    }

    /**
     * Creates a new user of the type User
     * @param user String input that will be the users displayed name, not identifier in the system
     * @throws IllegalArgumentException if input is not a string
     * */
    public void signUp(String user, String password) {
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }
        facade.createUser(user, password);
    }

    /**
     * Gets the ChatId by the given ChatName: name
     * @param name String that represents id of a user in the system
     * @returns ChatIdByName
     * */
    public int getChatIdByName(String name) {
        return facade.getChatIdByName(name);
    }
}
