package com.winbro.penelope;

/**
 * Created by pdavis on 25/10/2016.
 */
public class PartTransaction {

    private AbstractNode from;
    private AbstractNode to;


    public AbstractNode getFrom() {
        return from;
    }

    public void setFrom(AbstractNode from) {
        this.from = from;
    }

    public AbstractNode getTo() {
        return to;
    }

    public void setTo(AbstractNode to) {
        this.to = to;
    }
}
