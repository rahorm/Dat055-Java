package Controller;

import Model.ChatRoomFacade;

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
     * @param user integer value that represents a user in the active chat that is the sender of the message
     * @throws IllegalArgumentException if userID is not an existing user
     * @throws NumberFormatException if msg isn't a string
     * */
    public void sendMessage(String msg, String user){
        System.out.println("message entered controller");
        if (msg == null || msg.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        facade.storeMsg(msg);
        System.out.println("message left controller");
    }
    /**
     * Removes the chatRoom, you can not remove the activeChatRoom
     * @param chatID integer that represents a chatRoom
     * @throws IllegalArgumentException if chatID does not represent a chatRoom that exists
     * @throws IllegalArgumentException if the chatID given is the activeChatRoom
     * */
    public void removeChatRoom(int chatID) {
        if (chatID < 0) {
            throw new IllegalArgumentException("Invalid chatID");
        }
        // assuming facade/model will check active room constraint
        facade.removeChatRoom(chatID);
    }

    /**
     * Creates a new ChatRoom and makes that the activeChatRoom
     * @throws IllegalStateException if the max amount of chatRooms have been reached
     * */
    public void addChatRoom() {
        //@todo add chatname as argument, fix hardcoding
        facade.createChatRoom("chatName"); // need a user info
        facade.changeActiveRoom(facade.getMSGList().size());
    }

    /**
     * sets the active user to the input id
     *
     * @param user String that represents id of a user in the system
     * @throws IllegalArgumentException if id is not associated with an id that exists in the system
     * */
    public void changeActiveUser(String user) {
        facade.addMember(user);
    }


    public void login(String user, String password){
        facade.setActiveUser(user);
        facade.logIn(user, password);
        /*ReturnCode code;

        if(!facade.checkUser(user)){
            code = ReturnCode.USER_NOT_EXIST;
        }

        if(!facade.logIn(user, password)){
            code = ReturnCode.INCORRECT_PASSWORD;
        }

        code = ReturnCode.LOGIN_SUCCESSFUL;

        return code;*/
    }

    public void addMember(String user) {facade.addMember(user);}

    /**
     * Creates a new user of the type User
     *
     * @param user String input that will be the users displayed name, not identifier in the system
     * @throws IllegalArgumentException if input is not a string
     * */
    public void signUp(String user, String password) {
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }
        //@todo add checkUser before
        facade.createUser(user, password);
    }
}
