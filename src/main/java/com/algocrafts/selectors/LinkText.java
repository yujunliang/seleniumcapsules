package com.algocrafts.selectors;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.linkText;

/**
 * This enum has elements with ByLinkText from Selenium By API.
 */
public enum LinkText implements Supplier<By> {

    DISCOVER_MORE_EVENT("Discover More Events"),
    CANADA("CANADA"),
    ALL_CANADA("All Canada"),
    CHANGE_LOCATION("change location"),
    ACTIVE_MQ_IN_ACTION("ActiveMQ in Action"),
    JAVA("Java");

    private final By by;

    private LinkText(String id) {
        this.by = linkText(id);
    }

    /**
     * @return the by instance variable which is a ByLinkText.
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
