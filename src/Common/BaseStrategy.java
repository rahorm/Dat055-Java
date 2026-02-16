package Common;


import Other.ServerConnection;

/**
 * This is an interface for how a strategy for handling a specific iteraction between Server and ServerConnection should
 * look like
 * */
public interface BaseStrategy {
    void handler(ServerConnection connection);
}

