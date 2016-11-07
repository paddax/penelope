package com.winbro.penelope.engine;

import com.winbro.penelope.Transaction;
import com.winbro.penelope.data.NodeRequest;
import com.winbro.penelope.data.NodeUpdate;
import com.winbro.penelope.queue.IDispatch;

/**
 * Created by Peter Davis on 30/10/2016.
 */
public interface IF4Engine {

    IDispatch getDispatch();
    NodeUpdate nodeUpdate(NodeRequest request);

    NodeUpdate addPart(NodeRequest request);

    /**
     *
     * @param request
     * @return
     */
    NodeUpdate removePart(NodeRequest request);


    Transaction getTransaction(long transactionId);

    IF4Persistence getPersistence();
}
