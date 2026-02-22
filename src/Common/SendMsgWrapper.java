package Common;

import Other.Message;

import java.io.Serializable;

public class SendMsgWrapper implements Serializable {

    private Message msg;

    public SendMsgWrapper(Message msg) {
        this.msg = msg;
    }

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }
    @Override
    public String toString(){
        return "SendMsgWrapper, contains msg: "+msg.toString();
    }
}
