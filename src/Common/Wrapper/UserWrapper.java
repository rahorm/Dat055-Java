package Common.Wrapper;

import Other.Message;

import java.io.Serializable;

public class UserWrapper implements Serializable {

    private Message msg;

    public UserWrapper(Message msg) {
        this.msg = msg;
    }
}
