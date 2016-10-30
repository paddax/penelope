package org.fsd.penelope.engine;

import org.fsd.penelope.FixtureType;
import org.fsd.penelope.INode;
import org.fsd.penelope.PartType;

import java.util.List;

/**
 * Created by Peter Davis on 30/10/2016.
 */
public interface IF4Engine {

    void setNodes(List<INode> nodes);
    void setFixtureTypes(List<FixtureType> fixtures);
    void setPartTypes(List<PartType> partTypes);

}
