package com.winbro.penelope.queuetest;

import com.winbro.penelope.queue.BlockingDispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Peter Davis on 29/10/2016.
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static BlockingDispatch queue;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {

        queue = new BlockingDispatch();

        new Thread(new Worker()).start();
        new Thread(new WorkerR()).start();


        while (true) {
            queue.dispatch();
        }
    }


    private class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                try {
                    queue.send(() -> logger.debug("We are processing") );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class WorkerR implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                try {

                    queue.send(() -> {
                        logger.debug("We are processing for result");
                        return new StringBuilder();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
