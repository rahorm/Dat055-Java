package Other;

import java.time.LocalTime;
import java.util.Random;

public final class IdGenerator {

    private static IdGenerator instance;

    private IdGenerator(){}

    /**
     * If no IdGenerator has been initialized, intializes one and returns it. Otherwise returns the existing IdGenerator.
     * @return IdGenerator
     */
    public static  IdGenerator getInstance(){
        if (instance == null){
            instance = new IdGenerator();
        }
        return instance;
    }

    /**
     * Generates an unique id
     *
     * @return int generated id
     */
    public int generateId() {
        Random r = new Random();
        int randNum = r.nextInt(9999 - 999) + 999;
        LocalTime t = LocalTime.now();
        return (t.getMinute()*100000)+(t.getSecond()*1000)+randNum;
    }
}
