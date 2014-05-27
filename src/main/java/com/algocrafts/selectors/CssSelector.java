package com.algocrafts.selectors;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.cssSelector;

/**
 * This enum has elements with ByCssSelector from Selenium By API.
 */
public enum CssSelector implements Supplier<By> {

    UPDATE("input[value='Update']"),
    CONTINUE("input[value='Continue']");

    private final By by;

    private CssSelector(String id) {
        this.by = cssSelector(id);
    }

    /**
     * @return the by instance variable which is a ByCssSelector.
     */
    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}
