package Model;

import java.util.Observable;

public class ChatRoomFacade extends Observable {
    private ChatRoomModel model;


    public ChatRoomFacade(ChatRoomModel model) {
        this.model = model;
    }

    /**
     * User wants to change an active room, the chatroom that user wants to
     * open will be identified by chatID, this chatID should be sent as a parameter
     * when it's called in Control.
     *
     * @param chatID of a chatroom that user wants to open
     */
    public void changeActiveRoom(int chatID) {
        model.changeActiveRoom(chatID);

        setChanged(); // Apparently it should be used before using notifyObservers

        notifyObservers(Object o);
    }

		 /* Not needed now but can be useful to have/know
		 public ChatRoomModel getState(){
			 return this.model;
    } */
}