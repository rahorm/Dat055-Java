package Common;


import Other.ServerConnection;

/**
 * This is the class that the rest of our program interacts with
 * Common is either called by ServerConnection or by Server
 * and depending on ####how#### one of these methods will be invoked? */

public class Common {

    private BaseStrategy strategy;
    /**
     * This is the constructor that assigns strategies to an action
     * */
    public Common(BaseStrategy strategy) {
        this.strategy = strategy;
    }

    // allows changing strategy dynamically
    public void setStrategy(BaseStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(ServerConnection connection) {
        strategy.handler(connection);
    }
}


