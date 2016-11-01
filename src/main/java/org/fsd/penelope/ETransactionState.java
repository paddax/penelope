package org.fsd.penelope;

/**
 * Created by pdavis on 25/10/2016.
 */
public enum ETransactionState {
    /**
     * The node is not in any transaction state
     */
    IDLE,

    /**
     * Is a receiving station and an offer is made
     */
    OFFER_RECEIVE,
    ACCEPTED_RECEIVE,
    TRANSIT_RECEIVE,
    LOGIN_RECEIVE,
    COMPLETE_RECEIVE,
    /**
     * Is a sending station and a transaction has been made
     */
    OFFER_SEND,
    ACCEPTED_SEND,
    LOGIN_SEND,
    TRANSIT_SEND,

    COMPLETE_SEND

}
