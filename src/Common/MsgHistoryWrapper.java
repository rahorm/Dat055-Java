package Common;

import Other.Message;

import java.io.Serializable;
import java.util.ArrayList;

public class MsgHistoryWrapper implements Serializable {
    private ArrayList<Message> msgHistory;

    public MsgHistoryWrapper(ArrayList<Message> msgHistory) {
        this.msgHistory = msgHistory;
    }

    public ArrayList<Message> getMsgHistory() {
        return msgHistory;
    }

    public void setMsgHistory(ArrayList<Message> msgHistory) {
        this.msgHistory = msgHistory;
    }
}
