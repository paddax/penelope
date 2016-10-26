package org.fsd.penelope;

import java.util.EnumSet;

public enum EProcessState {

    /**
     * The process has not started
     */
    VIRGIN,

    /**
     * The process has started but is not complete
     */
    INPROCESS,

    /**
     * The process is complete
     */
    COMPLETE;

    private final int weight;
    private static int all = 0;

    static {
        EnumSet.allOf(EProcessState.class).forEach(x -> all += x.getWeight());
    }

    EProcessState() {
        this.weight = 1 << ordinal();
    }

    public int getWeight() {
        return weight;
    }

    ;

    public static int getAll() {
        return all;
    }

}
