package View;

import Controller.Controller;
import Model.ChatRoomFacade;
import Other.Message;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ChatRoomView implements Observer {
    Controller controller;
    ChatRoomFacade model;
    UI ui;
    /**
     * Used by an observable running notifyObservers() or notifyObservers(Object arg)
     * @param o
     * @param arg String representing what has changed in the model
     *            "user" when the active user has changed
     *            "message" when a new message has arrived
     *            when arg isn't specified everything in the view gets updated
     */
    public void update(Observable o, Object arg) {
        //messages
        ArrayList<Message> msg = model.getMSGList();
        ui.setMsg(msg);
        //  available chats
        ArrayList<String> chatNames = model.getAvailableChatNames();
        ArrayList<Integer> chatIds = model.getAvailableChatIds();
        ui.setAvailableChats(chatNames, chatIds);
    }

    public ChatRoomView(Controller controller, ChatRoomFacade model) {
        this.controller = controller;
        this.model = model;
        this.ui = new UI(controller);
        model.addObserver(this);
        update(model, null);

    }
}
