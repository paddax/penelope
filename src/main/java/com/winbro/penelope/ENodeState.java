package com.winbro.penelope;

/**
 * Created by Peter Davis on 02/11/2016.
 */
public enum ENodeState {

    /**
     * The node can accept or offer parts
     */
    READY,

    /**
     * The node is processing and can't accept or offer parts
     */
    PROCESSING,

    /**
     * The node is can no longer be used
     */
    ERROR
}
