package TuringMachine;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class Transfer {
    public String originalState;
    public String nextState;
    public Character inputChar;
    public Character labelChar;
    public Character direction;
    public int step;

    public Transfer(String originalState, String nextState, Character inputChar, Character labelChar, Character direction) {
        this.originalState = originalState;
        this.nextState = nextState;
        this.inputChar = inputChar;
        this.labelChar = labelChar;

        if (direction != 'L' && direction != 'R'){
            throw new ValueException("Expect the direction is 'L' or 'R', but got " + direction);
        }
        this.direction = direction;
        this.step = direction == 'L'? -1 :1;
    }

    public String toString() {
        return "{" +
                originalState + "->" + nextState + ":" +
                inputChar + "->(" + labelChar + "," + direction + ")" +
                "}";
    }
}
