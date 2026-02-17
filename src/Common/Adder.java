package Common;

import Common.Wrapper.ChatRoomWrapper;
import Common.Wrapper.UserWrapper;
import Common.Wrapper.MsgWrapper;

// concrete ServerVisitor that implements based on the input
class Adder implements ServerVisitor {

    @Override
    public void visit(MsgWrapper m) {
        System.out.println("logic for putting a msg in the server");
    }

    @Override
    public void visit(ChatRoomWrapper c) {

    }

    @Override
    public void visit(UserWrapper u) {

    }
}
