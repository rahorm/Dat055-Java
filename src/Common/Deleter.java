package Common;

import Common.Wrapper.ChatRoomWrapper;
import Common.Wrapper.MsgWrapper;
import Common.Wrapper.UserWrapper;

// concrete ServerVisitor that implements based on the input
public class Deleter implements ServerVisitor {

    @Override
    public void visit(MsgWrapper s) {

    }

    @Override
    public void visit(ChatRoomWrapper cr) {

    }

    @Override
    public void visit(UserWrapper cu) {

    }
}
