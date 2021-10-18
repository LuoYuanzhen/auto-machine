package NFA;

import java.util.*;

public class NFA {
    private State beginState;
    private Set<State> endStateSet;

    private Set<State> states;
    private Set<Character> inputChars;

    private List<Transition> transitions;


    public NFA(List<Transition> transitions){
        this.transitions = transitions;

        this.states = new HashSet<>();
        this.inputChars = new HashSet<>();

        this.endStateSet = new HashSet<>();
        for (Transition transition: transitions){
            this.states.add(transition.begin);
            this.states.add(transition.end);
            this.inputChars.add(transition.c);
        }

        for (State state: this.states){
            if (state.isInitState()){
                this.beginState = state;
            }
            else if (state.isTerminalState()){
                this.endStateSet.add(state);
            }
        }
    }

    private List<State> getClosureState(State state){
        List<State> closure = new ArrayList<>();
        for (Transition transition: this.transitions){
            if (transition.begin.getId().equals(state.getId()) && transition.c == 'e'){
                closure.add(transition.end);
            }
        }

        return closure;
    }

    public boolean matchString(String s){
        System.out.println("Matching string: "+s);
        Set<State> currentStates = new HashSet<>();
        currentStates.add(this.beginState);

        // calculate initial closure set
        currentStates.addAll(this.getClosureState(this.beginState));

        for (char c: s.toCharArray()){
            // looking for next states
            Set<State> nextStates = new HashSet<>();
            for (State state: currentStates) {
                for (Transition transition : this.transitions) {
                    if (transition.begin.getId().equals(state.getId()) && transition.c == c) {
                        nextStates.add(transition.end);
                    }
                }
            }

            if (nextStates.size() == 0){
                System.out.println("Failed matching: "+currentStates+","+c+"->[]");
                return false;
            }

            // calculate closure set
            Set<State> closure = new HashSet<>();
            for (State state: nextStates){
                closure.addAll(this.getClosureState(state));
            }
            nextStates.addAll(closure);

            System.out.println(currentStates+","+c+"->"+nextStates);
            currentStates = nextStates;
        }

        for (State state: currentStates) {
            if (state.isTerminalState()){
                System.out.println("Successful matching: "+state+" is a terminal state.");
                return true;
            }
        }

        System.out.println("Failed matching: there are no terminal states in "+currentStates);
        return false;
    }

    public void printNFA(){
        System.out.println("The information of NFA.NFA:");

        System.out.println("\t States Q: "+this.states);

        System.out.println("\t Initial NFA.State S: "+this.beginState);

        System.out.println("\t Terminals F: "+this.endStateSet);

    }
    public static void main(String[] args) {
        State q1 = new State("q1", true, false);
        State q2 = new State("q2", false, false);
        State q3 = new State("q3", false, false);
        State q4 = new State("q4", false, true);
        List<Transition> transitions = new ArrayList<Transition>(){
            {
                add(new Transition(q1, q1, '0'));
                add(new Transition(q1, q1, '1'));
                add(new Transition(q1, q2, '1'));
                add(new Transition(q2, q3, '0'));
                add(new Transition(q2, q3, 'e'));
                add(new Transition(q3, q4, '1'));
                add(new Transition(q4, q4, '0'));
                add(new Transition(q4, q4, '1'));
            }
        };

        NFA nfa = new NFA(transitions);
        nfa.printNFA();

        System.out.println(nfa.matchString("001101"));
        System.out.println(nfa.matchString("000"));
        System.out.println(nfa.matchString("000100"));
    }
}