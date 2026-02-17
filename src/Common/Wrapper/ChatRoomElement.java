package Common.Wrapper;

import Common.Visitor;

import java.io.Serializable;

/**
 * Concrete Elements
 *
 * These classes implement the Element interface and represent the various types of objects in the structure.
 * Each concrete element defines how it accepts a visitor by calling the corresponding method on the visitor.
 * */
public class ChatRoomElement implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}