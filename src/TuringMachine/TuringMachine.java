package TuringMachine;

import java.util.ArrayList;
import java.util.List;

public class TuringMachine {
    private Tape tape;

    private final String initState = "start";

    private final String terminalState = "accept";

    private final List<Transfer> transfers = new ArrayList<Transfer>(){
        {
            add(new Transfer("start", "q0", '#', null, 'R'));
            add(new Transfer("q0", "q0", '*', null, 'R'));
            add(new Transfer("q0", "q1", '+', null, 'R'));
            add(new Transfer("q1", "q1", '*', null, 'R'));
            add(new Transfer("q1", "q2", '=', null, 'R'));
            add(new Transfer("q2", "q2", '*', null, 'R'));
            add(new Transfer("q2", "q9", ' ', null, 'L'));

            add(new Transfer("q9", "q9", '+', null, 'L'));
            add(new Transfer("q9", "q9", '*', null, 'L'));
            add(new Transfer("q9", "q9", '=', null, 'L'));
            add(new Transfer("q9", "q9", 'x', null, 'L'));
            add(new Transfer("q9", "q9", ' ', null, 'L'));

            add(new Transfer("q9", "q3", '#', null, 'L'));
            add(new Transfer("q3", "q4", '#', null, 'R'));
            add(new Transfer("q4", "q4", 'x', null, 'R'));
            add(new Transfer("q4", "q4", '+', null, 'R'));
            add(new Transfer("q4", "q5", '*', 'x', 'R'));

            add(new Transfer("q4", "q6", '=', null, 'R'));
            add(new Transfer("q6", "q6", 'x', null, 'R'));
            add(new Transfer("q6", "accept", ' ', null, 'R'));

            add(new Transfer("q5", "q5", '*', null, 'R'));
            add(new Transfer("q5", "q5", '+', null, 'R'));
            add(new Transfer("q5", "q7", '=', null, 'R'));
            add(new Transfer("q7", "q7", 'x', null, 'R'));
            add(new Transfer("q7", "q9", '*', 'x', 'R'));
        }
    };

    private Transfer getTransfer(String currentState, char inputChar){
        for (Transfer transfer: this.transfers){
            if (transfer.originalState.equals(currentState) && transfer.inputChar == inputChar){
                return transfer;
            }
        }

        return null;
    }

    public boolean matchString(String inputString){
        this.tape = new Tape(inputString);

        System.out.println("Start matching: " + inputString);
        String currentState = this.initState;
        Character inputChar = inputString.charAt(0);

        while (inputChar != null){
            Transfer transfer = this.getTransfer(currentState, inputChar);

            System.out.print(transfer + ";tape:" + this.tape + "->");
            if (this.tape.applyMove(transfer)){
                currentState = transfer.nextState;
                inputChar = this.tape.getTapeHead();
                System.out.println(this.tape);
            }else {
                inputChar = null;
            }
        }

        if (currentState.equals(this.terminalState)){
            System.out.println("Matching successfully!");
            return true;
        }

        System.out.println("Matching failed.");
        return false;
    }

    public static void main(String[] args) {
        TuringMachine tm = new TuringMachine();
        tm.matchString("#*****+****=******* ");
    }
}
