package com.algocrafts.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface ExplicitWait<Where extends SearchScope<Where>> {

    /**
     * Save the screenshot if possible.
     */
    void onTimeout();

    /**
     * @param by selector
     * @return the element found by using the locator
     * @throws NoSuchElementException not found
     */
    default Element until(Supplier<By> by) throws NoSuchElementException {
        return until(30, SECONDS, by);
    }

    /**
     * @param predicate predicate
     * @throws TimeoutException timeout
     */
    default void until(Predicate<Where> predicate) throws TimeoutException {
        until(30, SECONDS, predicate);
    }

    /**
     * @param duration timeout duration
     * @param timeUnit unit
     * @param by       selector
     * @return the element found by using the locator
     * @throws NoSuchElementException not found
     */
    default Element until(int duration, TimeUnit timeUnit, Supplier<By> by) throws NoSuchElementException {
        try {
            return explicitWait(duration, timeUnit).until((Where where) -> where.findElement(by.get()));
        } catch (TimeoutException e) {
            onTimeout();
            throw new NoSuchElementException("Nothing found by " + by, e);
        }
    }

    /**
     * @param duration  timeout duration
     * @param timeUnit  unit
     * @param predicate predicate
     * @throws TimeoutException timeout
     */
    default void until(int duration, TimeUnit timeUnit, Predicate<Where> predicate) throws TimeoutException {
        try {
            explicitWait(duration, timeUnit).until((Where where) -> predicate.test(where));
        } catch (TimeoutException e) {
            onTimeout();
            throw e;
        }
    }

    /**
     * @param duration timeout duration
     * @param timeUnit unit
     * @return the FluentWait instance
     */
    @SuppressWarnings("unchecked")
    default FluentWait<Where> explicitWait(int duration, TimeUnit timeUnit) {
        return new FluentWait<>((Where) this)
                .withTimeout(duration, timeUnit)
                .pollingEvery(5, MILLISECONDS)
                .ignoring(Exception.class);
    }
}
