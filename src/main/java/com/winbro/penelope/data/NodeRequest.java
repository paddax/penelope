package com.winbro.penelope.data;

import com.winbro.penelope.ETransactionState;

/**
 * Created by Peter Davis on 02/11/2016.
 */
public class NodeRequest {

    private long nodeId;
    private ETransactionState requestTransaction;
    private long authorisation;
    private long transactionId;
    private String partName;
    private String agent;

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public long getAuthorisation() {
        return authorisation;
    }

    public void setAuthorisation(long authorisation) {
        this.authorisation = authorisation;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public ETransactionState getRequestTransaction() {
        return requestTransaction;
    }

    public void setRequestTransaction(ETransactionState requestTransaction) {
        this.requestTransaction = requestTransaction;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
}
