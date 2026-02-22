package Common;

import java.io.Serializable;

public class RequestWrapper implements Serializable {

    private RequestType type;
    private Object data; // can hold Message, User, ChatRoom, String, etc.

    public RequestWrapper(RequestType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public RequestType getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "RequestWrapper type=" + type + ", data=" + data;
    }
}
