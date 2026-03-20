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

    public ChatRoomView(Controller controller, ChatRoomFacade model) {
        this.controller = controller;
        this.model = model;
        this.ui = new UI(controller);
        model.addObserver(this);
        update(model, null);
    }

    /**
     * Used by an observable running notifyObservers() or notifyObservers(Object arg)
     * @param o Represents which observer is used as an Observable object, s not used in this version.
     * @param arg String representing what has changed in the model, is not used in this version. Can be set to 0.
     */
    public void update(Observable o, Object arg) {
        //messages
        ArrayList<Message> msg = model.getMSGList();
        ui.setMsg(msg);
        //available chats
        ArrayList<String> chatNames = model.getAvailableChatNames();
        ArrayList<Integer> chatIds = model.getAvailableChatIds();
        ui.setAvailableChats(chatNames, chatIds);
        //status message
        String statusMessage = model.getStatusMessage();
        if (statusMessage != null) {
            ui.displayStatusMessage(statusMessage);
        }
        ui.setActiveChat(model.getActiveChatRoomName());
    }
}
