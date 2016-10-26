package org.fsd.penelope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Peter Davis on 25/10/2016.
 */
public class Machine extends AbstractNode {

    private Part part;
    private boolean machineBusy;

    public Machine(String name) {
        super(name);
    }

    @Override
    public Part getHead() {
        return part;
    }

    @Override
    public Part removeHead() {
        Part result = part;
        part = null;
        return result;
    }

    @Override
    public boolean hasPartAvailable() {
        return part != null && this.getTransactionState() == ETransactionState.IDLE && !machineBusy;
    }

    @Override
    public List<Part> listParts() {
        if (part != null)
            return Arrays.asList(part);
        return new ArrayList<>();
    }

    @Override
    public int getPartCount() {
        return part != null ? 1 : 0;
    }
}
