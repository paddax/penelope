package org.fsd.penelope.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by Peter Davis on 29/10/2016.
 */
public class Dispatch implements IDispatch {

    private final LinkedTransferQueue<Message> queue;
    private static final Logger logger = LoggerFactory.getLogger(Dispatch.class);

    public Dispatch() {
        this.queue = new LinkedTransferQueue<>();
    }

    private class Message<T> {
        Runnable runnable;
        LinkedTransferQueue<Message<T>> response;
        Callable callable;
        T object;

        public Message(Callable e) {
            callable = e;
            object = null;
        }

        public Message(Runnable e) {
            runnable = e;
        }
    }

    @Override
    public void send(Runnable e) throws InterruptedException {
        queue.transfer(new Message(e));
    }

    @Override
    public <T> T send(Callable<T> r) throws InterruptedException {
        Message<T> m = new Message(r);
        m.response = new LinkedTransferQueue<>();
        queue.transfer(m);
        Message<T> x = m.response.take();
        return x.object;

    }

    public void dispatch() {
        try {
            Message x = queue.take();
            try {
                if (x.runnable != null) {
                    x.runnable.run();
                } else if (x.callable != null){
                    x.object = x.callable.call();
                    x.response.transfer(x);
                }
            } catch (Exception e) {
                logger.error("Dispatch method failed", e);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
