package com.winbro.penelope.data;

import com.winbro.penelope.ETransactionState;

import java.util.HashMap;
import java.util.Map;

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
    private long transactionId;
    private ETransactionState transactionState;
    private String partName;
    private String agent;
    private Map<Integer,PartData> partMap = new HashMap<>();

    public NodeUpdate(ETransactionState state, NodeRequest request) {
        this.transactionState = state;
        this.setNodeId(request.getNodeId());
    }

    public NodeUpdate() {

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

    public Map<Integer, PartData> getPartMap() {
        return partMap;
    }

    public void setPartMap(Map<Integer, PartData> partMap) {
        this.partMap = partMap;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
}
