package com.algocrafts.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface Waitable<Where> {

    /**
     * Save the screenshot if possible.
     */
    void save();

    /**
     * @param locator
     * @param <What>
     * @return
     * @throws NoSuchElementException
     */
    default public <What> What until(Locator<Where, What> locator) {
        return until(30, SECONDS, locator);
    }

    /**
     * @param predicate
     * @throws TimeoutException
     */
    default public void until(Predicate<Where> predicate) {
        until(30, SECONDS, predicate);
    }

    /**
     * @param duration
     * @param timeUnit
     * @param locator
     * @param <What>
     * @return
     * @throws NoSuchElementException
     */
    default public <What> What until(int duration, TimeUnit timeUnit, Locator<Where, What> locator) {
        try {
            return getFluentWait(duration, timeUnit).until((Where where) -> locator.locate(where));
        } catch (TimeoutException e) {
            save();
            throw new NoSuchElementException("Nothing found by " + locator, e);
        }
    }

    /**
     * @param duration
     * @param timeUnit
     * @param predicate
     * @throws TimeoutException
     */
    default public void until(int duration, TimeUnit timeUnit, Predicate<Where> predicate) {
        try {
            getFluentWait(duration, timeUnit).until((Where where) -> predicate.test(where));
        } catch (TimeoutException e) {
            save();
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    default public FluentWait<Where> getFluentWait(int duration, TimeUnit timeUnit) {
        return new FluentWait<>((Where) this)
                .withTimeout(duration, timeUnit)
                .pollingEvery(5, MILLISECONDS)
                .ignoring(Exception.class);
    }
}
