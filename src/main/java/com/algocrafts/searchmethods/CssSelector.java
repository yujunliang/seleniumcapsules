package com.algocrafts.searchmethods;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.cssSelector;

public enum CssSelector implements Supplier<By> {

    UPDATE("input[value='Update']"),
    CONTINUE("input[value='Continue']")
    ;

    private final By by;

    private CssSelector(String id) {
        this.by = cssSelector(id);
    }

    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}
