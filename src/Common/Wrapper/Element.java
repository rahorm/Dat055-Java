package Common.Wrapper;

import Common.Visitor;

/**
 * Element interface
 *
 * This interface defines an accept method that takes a visitor as an argument.
 * This method allows the visitor to visit the concrete elements.
 * */
public interface Element {
    void accept(Visitor visitor);
}


