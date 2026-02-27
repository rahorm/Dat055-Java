package Controller;

import Model.ChatRoomFacade;

public class Controller {

    private final ChatRoomFacade facade;

    public Controller(ChatRoomFacade modelF) {
        this.facade = modelF;
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
        facade.createChatRoom(); // need a user info
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

    /**
     * Checks if user is present in database
     *
     * @return if user exists or not
     * @throws IllegalStateException if the program is in a state where user has not been chosen yet
     * */
    //@todo implement - check if user in database
    public boolean checkUser() {
        String activeUser = facade.getActiveUser();

        if (activeUser == null) {
            throw new IllegalStateException("No active user set");
        }

        return true;
    }

    public boolean login(String user, String password){
        /*
        kolla om användaren finns
        om ja, kolla lösenord
        om nej, skicka felmeddelande
        lösenord korrekt -> set active user

        */
        ChatRoomFacade facade = Model.ChatRoomFacade.getInstance(); //test
        facade.setActiveUser(user);// För att prova att skicka meddelanden
        return true;
    }

    /**
     * Creates a new user of the type User
     *
     * @param user String input that will be the users displayed name, not identifier in the system
     * @throws IllegalArgumentException if input is not a string
     * */
    public void createUser(String user, String password) {
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }
        facade.addMember(user);
    }
}
