package com.algocrafts.selectors;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

/**
 * This enum has elements with ById from Selenium By API.
 */
public enum Id implements Supplier<By> {

    MAIN_NAV("main-nav"),
    UI_DATEPICKER_DIV("ui-datepicker-div"),
    CONTENT("content"),
    DATE_PICKER("datepicker");

    private final By by;

    private Id(String id) {
        this.by = id(id);
    }

    /**
     * @return the by instance variable which is a ById.
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