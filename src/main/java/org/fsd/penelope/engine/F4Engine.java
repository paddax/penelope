package org.fsd.penelope.engine;

import org.fsd.penelope.ETransactionState;
import org.fsd.penelope.FixtureType;
import org.fsd.penelope.INode;
import org.fsd.penelope.PartType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Peter Davis on 30/10/2016.
 */
public class F4Engine implements IF4Engine {

    private Map<Long,INode> nodes = new HashMap<>();
    private List<FixtureType> fixtures;
    private List<PartType> partTypes;

    private boolean terminated = false;

    private Runnable server = new Runnable() {
        @Override
        public void run() {
            while(!terminated) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }


            }
        }
    };

    @Override
    public void setNodes(List<INode> nodes) {
        nodes.forEach(n -> this.nodes.put(n.getID(), n));
    }

    @Override
    public void setFixtureTypes(List<FixtureType> fixtures) {
        this.fixtures = fixtures;
    }

    @Override
    public void setPartTypes(List<PartType> partTypes) {
        this.partTypes = partTypes;
    }

    @Override
    public NodeUpdate ready(NodeRequest request) {
        INode node = nodes.get(request.getNodeId());
        if(node == null)
            return NodeUpdate.INVALIDNODE(request.getNodeId());

        ETransactionState rs = request.getTransactionState();

        return new NodeUpdate();
    }

    @Override
    public NodeUpdate login(NodeRequest request) {
        return NodeUpdate.INVALIDNODE(request.getNodeId());
    }


}
