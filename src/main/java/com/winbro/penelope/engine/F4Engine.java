package com.winbro.penelope.engine;

import com.winbro.penelope.*;
import com.winbro.penelope.data.NodeRequest;
import com.winbro.penelope.data.NodeUpdate;
import com.winbro.penelope.queue.BlockingDispatch;
import com.winbro.penelope.queue.IDispatch;

import java.util.*;

/**
 * Created by Peter Davis on 30/10/2016.
 */
public class F4Engine implements IF4Engine {

    private final IF4Persistence persistence;
    private Map<Long,INode> nodes = new HashMap<>();
    private Map<Long,FixtureType> fixtures = new HashMap<>();
    private Map<Long, PartType> partTypes = new HashMap<>();
    private BlockingDispatch dispatch = new BlockingDispatch();
    private List<Part> parts = new ArrayList<Part>();

    private boolean terminated = false;

    private Runnable server = new Runnable() {
        @Override
        public void run() {
            dispatch.dispatch();
        }
    };

    public F4Engine(IF4Persistence persistence) {
        this.persistence = persistence;
    }

    public void setNodes(List<INode> nodes) {
        nodes.forEach(n -> this.nodes.put(n.getID(), n));
    }

    public void setFixtureTypes(List<FixtureType> fixt) {
        fixt.forEach(f -> fixtures.put(f.getId(), f));
    }

    public void setPartTypes(List<PartType> pts) {
        pts.forEach(p -> partTypes.put(p.getId(), p));
    }

    @Override
    public IDispatch getDispatch() {
        return dispatch;
    }

    @Override
    public NodeUpdate nodeUpdate(NodeRequest request) {
        INode node = nodes.get(request.getNodeId());
        if(request.getAuthorisation() == node.getAuthorisation()) {
            node.setLastContact(System.currentTimeMillis());
            return node.update(this, request);
        }

        return NodeUpdate.INVALIDNODE(request);
    }

    @Override
    public NodeUpdate addPart(NodeRequest request) {
        INode node = nodes.get(request.getNodeId());
        if(request.getAuthorisation() == node.getAuthorisation()) {
            Part p = persistence.findPart(node.getParttype(), request.getPartName());
            if(p == null) {
                p = new Part(node.getParttype(), request.getPartName());
            }
            if(p.getLocation() != null) {
                return NodeUpdate.INVALIDREQUEST(request);
            }
            parts.add(p);
            node.addPart(p);
        }
        return new NodeUpdate(request)
    }

    @Override
    public NodeUpdate removePart(NodeRequest request) {
        return null;
    }

    @Override
    public Transaction getTransaction(long transactionId) {
        return null;
    }

    public FixtureType getFixtureType(long fx) {
        return fixtures.get(fx);
    }

    public PartType getPartType(long pt) {
        return partTypes.get(pt);
    }

    public void start() {
        new Thread(server).start();
    }
}
