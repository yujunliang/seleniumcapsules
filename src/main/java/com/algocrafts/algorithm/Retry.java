package com.algocrafts.algorithm;

import java.util.concurrent.TimeUnit;

public class Retry {

    private boolean on;
    private long count;
    private final long interval;
    private final TimeUnit unit;

    /**
     * Construct a retry instance.
     *
     * @param count    count
     * @param interval interval
     * @param unit     unit
     */
    public Retry(int count, int interval, TimeUnit unit) {
        this.count = count;
        this.interval = interval;
        this.unit = unit;
    }

    public boolean done() {
        return count <= 0 || !on;
    }

    public Retry on() {
        this.on = true;
        return this;
    }

    public void off() {
        this.on = false;
    }

    public <T> T attempt(Attemptable<T> task) throws Exception {
        T result = perform(task);

        while (!done()) {
            rest();
            result = perform(task);
        }
        return result;
    }

    private <T> T perform(Attemptable<T> task) throws Exception {
        count--;
        try {
            T attempt = task.attempt();
            off();
            return attempt;
        } catch (Exception e) {
            on();
            e.printStackTrace();
            if (count == 0) {
                throw e;
            }
        }
        return null;
    }

    public long count() {
        return count;
    }

    private void rest() {
        try {
            unit.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "retrying " + count + " times in " + interval + " " + unit;
    }
}