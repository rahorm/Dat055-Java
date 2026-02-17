package Common.Wrapper;

import Other.Message;

import java.io.Serializable;

public class MsgWrapper implements Serializable {

    private Message msg;

    public MsgWrapper(Message msg) {
        this.msg = msg;
    }
}
