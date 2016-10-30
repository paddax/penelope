package org.fsd.penelope.engine;

import org.fsd.penelope.FixtureType;
import org.fsd.penelope.INode;
import org.fsd.penelope.PartType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Davis on 30/10/2016.
 */
public class F4Engine implements IF4Engine {

    private List<INode> nodes = new ArrayList<>();

    @Override
    public void setNodes(List<INode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void setFixtureTypes(List<FixtureType> fixtures) {

    }

    @Override
    public void setPartTypes(List<PartType> partTypes) {

    }
}
