package org.fsd.penelope;

/**
 * Created by pdavis on 25/10/2016.
 */
public enum ETransactionState {
    /**
     * The node is not in any transition state
     */
    IDLE,

    /**
     * Is a receiving station and an offer is made
     */
    OFFERED,

    /**
     * Is a sending station and a transaction has been made
     */
    OFFERING,

    ACCEPTED,
    LOGIN_SEND,
    TRANSIT,
    LOGIN_ARRIVE,
    COMPLETE

}
