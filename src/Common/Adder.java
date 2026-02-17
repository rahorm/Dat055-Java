package Common;

import Common.Wrapper.ChatRoomElement;
import Common.Wrapper.UserElement;
import Common.Wrapper.MsgElement;

/**
 * Concrete Servervisitor
 *
 * This class implements the Visitor interface and provides the specific behavior for each visit method.
 * It contains the logic for the operations that need to be performed on the elements.
 * */
class Adder implements Visitor {

    @Override
    public void visit(MsgElement m) {
        System.out.println("logic for putting a msg in the server");
    }

    @Override
    public void visit(ChatRoomElement c) {

    }

    @Override
    public void visit(UserElement u) {

    }
}
