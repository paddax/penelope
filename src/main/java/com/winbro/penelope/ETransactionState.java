package com.winbro.penelope;

/**
 * Created by pdavis on 25/10/2016.
 */
public enum ETransactionState {
    /**
     * The node is not in any transaction state
     */
    IDLE,

    /**
     * The transaction has been initiated
     */
    OFFER,
    LOGIN_SEND,
    TRANSIT,
    LOGIN_RECEIVE,

}
