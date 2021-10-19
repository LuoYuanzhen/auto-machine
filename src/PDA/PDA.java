package PDA;

import java.util.*;

public class PDA {
    private Stack<Character> stack;

    private final String initState = "q0";

    private final Set<String> terminalStates = new HashSet<String>(){
        {
            add("qe");
        }
    };

    private final List<Transfer> transfers = new ArrayList<Transfer>(){
        {
            add(new Transfer("q0", "qe", '#', '#', '$'));

            add(new Transfer("qe", "qb", 'b', '#', 'b'));
            add(new Transfer("qe", "qc", 'c', '#', 'c'));
            add(new Transfer("qe", "qe", 'a', '#', '#'));
            add(new Transfer("qe", "qe", '#', '$', '#'));

            add(new Transfer("qb", "qb", 'a', '#', '#'));
            add(new Transfer("qb", "qb", 'b', '#', 'b'));
            add(new Transfer("qb", "qb", 'c', 'b', '#'));
            add(new Transfer("qb", "qe", '#', '$', '$'));

            add(new Transfer("qc", "qc", 'a', '#', '#'));
            add(new Transfer("qc", "qc", 'c', '#', 'c'));
            add(new Transfer("qc", "qc", 'b', 'c', '#'));
            add(new Transfer("qc", "qe", '#', '$', '$'));
        }
    };

    private List<Transfer> getTransfers(String currentState, char inputChar){
        List<Transfer> transfers = new ArrayList<>();
        if (this.stack.isEmpty()){
            for (Transfer transfer: this.transfers){
                if (transfer.originalState.equals(currentState) && (transfer.inputChar == inputChar || transfer.inputChar == '#') &&
                        transfer.popChar == '#'){
                    transfers.add(transfer);
                }
            }
        }else{
            for (Transfer transfer: this.transfers){
                if (transfer.originalState.equals(currentState) && (transfer.inputChar == inputChar || transfer.inputChar == '#') &&
                        (transfer.popChar == this.stack.peek() || transfer.popChar == '#')){
                    transfers.add(transfer);
                }
            }
        }

        return transfers;
    }

    private boolean recurMatchString(String str, int beginIndex, String currentState, Stack<Character> stack, int depth){

        char inputChar;
        if (beginIndex >= str.length()){
            inputChar = '#';
        }else {
            inputChar = str.charAt(beginIndex);
        }

        List<Transfer> transfers = this.getTransfers(currentState, inputChar);
        if (transfers.size() == 0){
            if (beginIndex >= str.length() && this.terminalStates.contains(currentState)){
                System.out.println("Correct path!");
                return true;
            }else {
                System.out.println("Wrong path!");
                return false;
            }
        }

        String prefix = "";
        for (int i = 0; i < depth; i++){
            prefix += "  ";
        }

        for (Transfer transfer: transfers){
            System.out.print("-" + depth + ": " + prefix + transfer + ",");
            if (transfer.popChar != '#') {
                stack.pop();
            }
            if (transfer.pushChar != '#') {
                stack.push(transfer.pushChar);
            }

            System.out.println(stack + ";");
            boolean recurMatched;
            if (transfer.inputChar == '#' && !currentState.equals(this.initState)){
                recurMatched = recurMatchString(str, beginIndex, transfer.nextState, stack, depth+1);
            }else {
                recurMatched = recurMatchString(str, beginIndex+1, transfer.nextState, stack, depth+1);
            }
            if (recurMatched){
                return true;
            }

            // recover the stack
            if (transfer.pushChar != '#') {
                stack.pop();
            }
            if (transfer.popChar != '#') {
                stack.push(transfer.popChar);
            }
        }

        return false;
    }

    public boolean matchString(String str){
        this.stack = new Stack<>();
        str = "#" + str;

        System.out.println("Start matching:" + str);
        if (recurMatchString(str, 0, this.initState, stack, 0)){
            System.out.println("Matching Successfully!");
            return true;
        }
        System.out.println("Matching Failed.");
        return false;
    }

    public static void main(String[] args) {
        PDA experimentPDA = new PDA();
        experimentPDA.matchString("bccab");
        experimentPDA.matchString("aabbccabbcc");
        experimentPDA.matchString("ac");
        experimentPDA.matchString("ab");
        experimentPDA.matchString("acb");
    }
}
