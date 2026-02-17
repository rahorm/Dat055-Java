package Common;

import Common.Wrapper.ChatRoomWrapper;
import Common.Wrapper.MsgWrapper;
import Common.Wrapper.UserWrapper;

/** contains placeholder methods that gets called based on what input is used
 * the input is wrapper classes that make contet from our program serializable
 */
public interface ServerVisitor {
    void visit(MsgWrapper s);
    void visit(ChatRoomWrapper cr);
    void visit(UserWrapper cu);
}

