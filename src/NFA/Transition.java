package NFA;

public class Transition {
    public State begin;
    public State end;
    public char c;
    public Transition(State begin, State end, char c){
        this.begin = begin;
        this.end = end;
        this.c = c;
    }


}
