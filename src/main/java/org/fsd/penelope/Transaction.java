package org.fsd.penelope;

/**
 * Created by Peter Davis on 25/10/2016.
 */
public class Transaction {

    private INode tx;
    private INode rx;
    private Part part;

    public Transaction(INode tx, INode rx, Part part) {
        this.tx = tx;
        this.rx = rx;
        this.part = part;
    }
}
