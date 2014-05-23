package com.algocrafts.algorithm;

import java.util.concurrent.TimeUnit;

public class Duration {
    private final int duration;
    private final TimeUnit timeUnit;

    public Duration(int duration, TimeUnit timeUnit) {
        this.duration = duration;
        this.timeUnit = timeUnit;
    }

    public void elapse() {
        try {
            timeUnit.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
