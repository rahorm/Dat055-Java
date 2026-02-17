package Common;

import Common.Wrapper.ChatRoomElement;
import Common.Wrapper.MsgElement;
import Common.Wrapper.UserElement;

/**
 * Concrete Servervisitor
 *
 * This class implements the Visitor interface and provides the specific behavior for each visit method.
 * It contains the logic for the operations that need to be performed on the elements.
 * */
public class Deleter implements Visitor {

    @Override
    public void visit(MsgElement s) {

    }

    @Override
    public void visit(ChatRoomElement cr) {

    }

    @Override
    public void visit(UserElement cu) {

    }
}
