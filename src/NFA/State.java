package NFA;

public class State {
    private String id;
    private boolean isInitState;
    private boolean isTerminalState;

    public State(String id, boolean isInitState, boolean isTerminalState){
        this.id = id;
        this.isInitState = isInitState;
        this.isTerminalState = isTerminalState;
    }

    public String getId() {
        return id;
    }

    public boolean isInitState() {
        return isInitState;
    }

    public boolean isTerminalState() {
        return isTerminalState;
    }

    public String toString(){
        return this.id;
    }
}
