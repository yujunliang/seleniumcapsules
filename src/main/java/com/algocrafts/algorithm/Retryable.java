package com.algocrafts.algorithm;

import org.apache.log4j.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.log4j.Logger.getLogger;

public interface Retryable<T> {

    Logger LOGGER = getLogger(Retryable.class);

    default public T retry(Attemptable<T> task)  {
        final Retry retry = new Retry(3, 3, SECONDS);
        try {
            return retry.attempt(task);
        } catch (Exception e) {
            LOGGER.info(retry);
            throw new RuntimeException(e);
        }
    }
}