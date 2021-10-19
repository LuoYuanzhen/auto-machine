package PDA;

public class Transfer {
    public String originalState;
    public String nextState;

    public char inputChar;
    public char popChar;
    public char pushChar;

    public Transfer(String originalState, String nextState, char inputChar, char popChar, char pushChar) {
        this.originalState = originalState;
        this.nextState = nextState;
        this.inputChar = inputChar;
        this.popChar = popChar;
        this.pushChar = pushChar;
    }

    @Override
    public String toString() {
        return "{" +
                originalState + "->" + nextState + ":" +
                "(" + inputChar + "," + popChar + ")->" + pushChar +
                "}";
    }
}
