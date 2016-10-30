package org.fsd.penelope.queue;

import java.util.concurrent.Callable;

/**
 * Created by Peter Davis on 30/10/2016.
 */
public interface IDispatch {
    void send(Runnable e) throws InterruptedException;

    <T> T send(Callable<T> r) throws InterruptedException;
}
