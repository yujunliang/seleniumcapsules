package com.algocrafts.selectors;


import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.openqa.selenium.By.linkText;

/**
 * This enum is a Supplier of ByLinkText from Selenium By API.
 */
public enum LinkText implements Supplier<By> {

    DISCOVER_MORE_EVENT("Discover More Events"),
    CANADA("CANADA"),
    ALL_CANADA("All Canada"),
    CHANGE_LOCATION("change location"),
    ACTIVE_MQ_IN_ACTION("ActiveMQ in Action"),

    EXTJS_DATEPICKER_TRIGGER("choose a date"),

    JAVA("Java"),
    ONTARIO("Ontario");

    private final By by;
    private final String text;

    LinkText(String text) {
        this.by = linkText(text);
        this.text = text;
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

    public static LinkText resolve(String text) {
        return Stream.of(values()).filter( (LinkText t) -> t.text.equals(text)).findFirst().get();
    }
}
