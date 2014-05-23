package com.algocrafts.pages;

import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface Waitable<Where> {

    void save();

    default public <What> What until(Locator<Where, What> predicate) {
        return until(30, SECONDS, predicate);
    }

    default public void until(Predicate<Where> predicate) {
        until(30, SECONDS, predicate);
    }

    default public <What> What until(int duration, TimeUnit timeUnit, Locator<Where, What> locator) {
        try {
            return getFluentWait(duration, timeUnit).until((Where where) -> locator.apply(where));
        } catch (RuntimeException e) {
            save();
            throw e;
        }
    }

    default public void until(int duration, TimeUnit timeUnit, Predicate<Where> predicate) {
        try {
            getFluentWait(duration, timeUnit).until((Where where) -> predicate.test(where));
        } catch (RuntimeException e) {
            save();
            throw e;
        }
    }

    default public FluentWait<Where> getFluentWait(int duration, TimeUnit timeUnit) {
        return new FluentWait<>((Where) this).withTimeout(duration, timeUnit).pollingEvery(50, MILLISECONDS).ignoring(Exception.class);
    }
}
