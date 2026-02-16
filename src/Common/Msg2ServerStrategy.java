package Common;


import Other.ServerConnection;

/**
 * Class that gets a msg from Server Connection and sends it to server
 * A different class like this is made for every type of interaction server and server connection will have with eachother
 * */
public class Msg2ServerStrategy implements BaseStrategy {

    @Override
    public void handler(ServerConnection con) {
        System.out.println("Now msg is being sent to server");
    }
}

