package org.fsd.penelope.engine;

import org.fsd.penelope.ETransactionState;

/**
 * Created by Peter Davis on 01/11/2016.
 */
public class NodeRequest {
    private long nodeId;
    private ETransactionState transactionState;

    public long getNodeId() {
        return nodeId;
    }

    public ETransactionState getTransactionState() {
        return transactionState;
    }
}
