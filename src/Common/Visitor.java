package Common;

import Common.Wrapper.ChatRoomElement;
import Common.Wrapper.MsgElement;
import Common.Wrapper.UserElement;

/** This interface declares a visit method for each type of element in the object structure.
 * Each method is designed to handle a specific element type.
 */
public interface Visitor {
    void visit(MsgElement s);
    void visit(ChatRoomElement cr);
    void visit(UserElement cu);
}

