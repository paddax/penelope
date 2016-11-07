package com.winbro.penelope.data;

import com.winbro.penelope.ETransactionState;

/**
 * Created by Peter Davis on 01/11/2016.
 */
public class NodeUpdate {
    public static final NodeUpdate INVALIDNODE(NodeRequest request) {
        NodeUpdate x = new NodeUpdate();
        x.setNodeId(request.getNodeId());
        return x;
    };
    public static NodeUpdate INVALIDREQUEST(NodeRequest request) {
        NodeUpdate x = new NodeUpdate();
        x.setNodeId(request.getNodeId());
        return x;
    }
    public static NodeUpdate BOUNCE(NodeRequest request) {
        NodeUpdate x = new NodeUpdate();
        x.setNodeId(request.getNodeId());
        return x;
    }

    private long nodeId;
    private ETransactionState transactionState;
    private String partName;
    private String agent;

    public NodeUpdate(ETransactionState state, NodeRequest request) {
        this.transactionState = state;
        this.setNodeId(request.getNodeId());
    }

    public long getNodeId() {
        return nodeId;
    }

    public ETransactionState getTransactionState() {
        return transactionState;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public void setTransactionState(ETransactionState transactionState) {
        this.transactionState = transactionState;
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

}
