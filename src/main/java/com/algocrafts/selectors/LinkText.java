package com.algocrafts.selectors;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.linkText;

public enum LinkText implements Supplier<By> {

    ACTIVE_MQ_IN_ACTION("ActiveMQ in Action"),
    JAVA("Java");

    private final By by;

    private LinkText(String id) {
        this.by = linkText(id);
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
