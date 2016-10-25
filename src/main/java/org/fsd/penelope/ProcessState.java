package org.fsd.penelope;

import java.util.EnumSet;

/**
 * Created by Peter Davis on 25/10/2016.
 */
public class ProcessState {

    private int state;

    private ProcessState(int s) {
        this.state = s;
    }

    public ProcessState(EProcessState s) {
        this.state = s.getWeight();
    }

    public ProcessState(ProcessState s) {
        state = s.state;
    }

    public ProcessState(EnumSet<EProcessState> s) {
        set(s);
    }

    public ProcessState() {
        this.state = 0;
    }

    public boolean is(EProcessState s) {
        return s.getWeight() == state;
    }

    private void set(EnumSet<EProcessState> map) {
        state = 0;
        map.forEach(a -> state |= a.getWeight());
    }

    public ProcessState union(ProcessState s) {
        return new ProcessState(s.state | state);
    }

    public ProcessState intersection(ProcessState s) {
        return new ProcessState(s.state & state);
    }

    public ProcessState complement() {
        return new ProcessState(state ^ EProcessState.getAll());
    }

    public boolean isEmpty() {
        return state == 0;
    }
}
