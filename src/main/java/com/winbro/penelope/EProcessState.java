package com.winbro.penelope;

import java.util.EnumSet;

public enum EProcessState {

    NONE(0),
    /**
     * The process has not started
     */
    VIRGIN(1),

    /**
     * The process has started but is not complete
     */
    INPROCESS(2),

    NOT_COMPLETE(3),

    /**
     * The process is complete
     */
    COMPLETE(4),

    NOT_INPROCESS(5),

    NOT_VIRGIN(6),

    ALL(7);

    private final int weight;
    private static int all = 0;

    static {
        EnumSet.allOf(EProcessState.class).forEach(x -> all |= x.getWeight());
    }

    private EProcessState(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }


    public static int getAll() {
        return all;
    }
}
