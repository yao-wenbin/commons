package io.github.yaowenbin.commons.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * do something at a fixed rate.
 */
public abstract class Scheduler implements Runnable{

    private final AtomicBoolean started = new AtomicBoolean(false);

    private static final long JOIN_MAX_TIME = 60 * 1000;

    private Thread thread;

    public abstract void doSomething();

    @Override
    public void run() {
        while(started.get()) {
            waitForRunning(getInterval());
            doSomething();
        }
    }

    private void waitForRunning(long interval) {
        synchronized (this) {
            try {
                wait(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public Scheduler start() {
        this.started.compareAndSet(false, true);
        thread = new Thread(this);
        thread.start();
        return this;
    }

    public void shutdown() {
        started.compareAndSet(true, false);

        thread.interrupt();
        try {
            thread.join(JOIN_MAX_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public long getInterval() {
        return 500;
    }

    public boolean started() {
        return started.get();
    }

}
