package com.winbro.penelope;

import com.winbro.penelope.data.NodeRequest;
import com.winbro.penelope.data.NodeUpdate;
import com.winbro.penelope.engine.IF4Engine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FifoShelf extends AbstractNode {

    private LinkedList<Part> parts;

    public FifoShelf(String name) {
        super(name);
        this.parts = new LinkedList<>();

    }

    public void setMaxPartCount(int partCount) {
        this.partCount = partCount;
    }

    @Override
    public PartUpdate addPart(Part part) {
        parts.add(part);
        part.setLocation(this);
    }

    @Override
    public void removePart(Part p) {
        parts.remove(p);
        p.setLocation(null);
    }

    @Override
    public Part getHead() {
        return parts.getFirst();
    }

    @Override
    public Part removeHead() {
        return parts.removeFirst();
    }

    @Override
    public boolean hasPartAvailable() {
        return parts.size() > 0 && this.getTransaction() == null;
    }

    @Override
    public List<Part> listParts() {
        return Collections.unmodifiableList(this.parts);
    }

    @Override
    public int getPartCount() {
        return parts.size();
    }

    @Override
    public NodeUpdate update(IF4Engine f4, NodeRequest request) {
        if(getTransaction() == null) {
            // forced to idle no error checking
            return new NodeUpdate(ETransactionState.IDLE, request);
        }
        if(request.getRequestTransaction() != this.getTransactionState()) {
            // a state change is occuring

            switch(getTransactionState()) {
            }
        }
        return NodeUpdate.BOUNCE(request);
    }
}

