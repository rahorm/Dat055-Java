package Model;

import java.util.Observable;

public class ChatRoomFacade extends Observable {
    private ChatRoomModel model;

    public void addObserver(){

    }

    public void removeObserver(){

    }

    public void notifyObservers(){

    }

    /**
     * Takes an integer representing an existing chatRoom ID and makes that the active chatRoom
     * @param chatID integer representing a chatRoom
     * @throws IllegalArgumentException if chatID isn't an existing ID for a chatRoom
     * */
    public void changeActiveRoom(int chatID){

    }
}
