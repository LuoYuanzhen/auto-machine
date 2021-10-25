package TuringMachine;

import java.util.Arrays;

public class Tape {
    private char[] content;
    private int index;

    public Tape(String content){
        // add [] space indicates the end of the verified string
        this.content = content.toCharArray();
        this.index = 0;
    }

    public boolean applyMove(Transfer transfer){
        // check
        if (transfer == null || transfer.inputChar != this.content[this.index]){
            return false;
        }
        // apply move
        if (transfer.labelChar != null){
            this.content[this.index] = transfer.labelChar;
        }
        int nextIndex = this.index + transfer.step;
        this.index = nextIndex > 0 ? Math.min(nextIndex, this.content.length - 1) : 0;

        return true;
    }

    public char getTapeHead(){
        return this.content[this.index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.content.length; i++){
            if (i == this.index){
                sb.append("[" + this.content[i] + "]");
            }else {
                sb.append(this.content[i]);
            }
        }
        return "{" +
                sb.toString() +
                '}';
    }
}
