package com.winbro.penelope;

/**
 * Created by Peter Davis on 25/10/2016.
 */
public class Transaction {

    private long ID;
    private INode tx;
    private INode rx;
    private Part part;

    public Transaction(INode tx, INode rx, Part part) {
        this.tx = tx;
        this.rx = rx;
        this.part = part;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public INode getTx() {
        return tx;
    }

    public void setTx(INode tx) {
        this.tx = tx;
    }

    public INode getRx() {
        return rx;
    }

    public void setRx(INode rx) {
        this.rx = rx;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
