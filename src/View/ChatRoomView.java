package View;

import java.util.Observable;
import java.util.Observer;

public class ChatRoomView implements Observer {
    /**
     * Used by an observable running notifyObservers() or notifyObservers(Object arg)
     * @param o
     * @param arg String representing what has changed in the model
     *            "user" when the active user has changed
     *            "message" when a new message has arrived
     *            when arg isn't specified everything in the view gets updated
     */
    public void update(Observable o, Object arg) {

    }
}
