package Other;

public final class IdGenerator {

    private static IdGenerator instance;
    private int nextId;


    private IdGenerator(){
        this.nextId = 0;
    }

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
    int generateId(){
        int id = nextId;
        nextId++;
        return id;
    }
}
