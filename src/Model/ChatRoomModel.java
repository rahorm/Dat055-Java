package Model;

import Other.Messages;
import Other.Users;

import java.util.Observable;

public class ChatRoomModel extends Observable {
    private Messages[] msg;
    private Users[] members;
    private Users activeUser;
    private int chatID;
}