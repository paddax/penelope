package com.winbro.penelope;

import com.winbro.penelope.data.NodeRequest;
import com.winbro.penelope.data.NodeUpdate;
import com.winbro.penelope.engine.IF4Engine;

import java.lang.*;
import java.util.List;
import java.util.Map;

/**
 * Common interface for all node types
 * <p>
 * Created by Peter Davis on 25/10/2016.
 */
public interface INode {

    /**
     * Unique ID of node
     * @return
     */
    long getID();

    /**
     * Part type thats used by this node
     *
     * @return Parttype of this node
     */
    PartType getParttype();

    /**
     * Maximum number of parts in this node
     *
     * @return Number of parts this node can hold
     */
    int getMaxPartCount();

    /**
     * Fixture type required in input to node
     *
     * @return Input fixture type
     */
    FixtureType getIn();

    /**
     * Fixture type attached to part on exit from node
     *
     * @return Fixture on exit from node
     */
    FixtureType getOut();

    /**
     * Name of the node
     *
     * @return name
     */
    String getName();

    /**
     * Map of accept criteria, at least one accept criterion must be matched for the node to accept a part
     *
     * @return Map of accept criteria
     */
    Map<String, Process> accept();

    /**
     * Map of reject criteria, if any criterion is matched the node will not accept a part
     *
     * @return Map of reject criteria
     */
    Map<String, Process> reject();

    /**
     * Map of state changes this node may transform to
     *
     * @return Map of output states that differ from input states
     */
    Map<String, Process> output();

    /**
     * Part being offered
     *
     * @return
     */
    Part getHead();

    /**
     * Removes the only part that can be removed
     *
     * @return The part that was removed
     */
    Part removeHead();

    Transaction getTransaction();

    void setTransaction(Transaction t);

    /**
     * @param part Determines if this node wants the specified part
     * @return True if this node can accept the part
     */
    boolean wants(Part part);

    /**
     * Determines if a part is available
     *
     * @return True if this node is offering a part
     */
    boolean hasPartAvailable();

    /**
     * Lists the parts contained in this node, the first part in the list is the next part offered
     *
     * @return List of parts in this node
     */
    List<Part> listParts();

    /**
     * Determines the number of parts in this node
     *
     * @return Number of parts in node
     */
    int getPartCount();

    /**
     * Request from the connected node.
     * <p></p>Must be called from the Factory thread
     *
     * @param f4
     * @param request
     * @return
     */
    NodeUpdate update(IF4Engine f4, NodeRequest request);

    long getAuthorisation();

    void setLastContact(long ts);

    long getLastContact();


    NodeUpdate addPart(Part p);

    void removePart(Part p);
}
