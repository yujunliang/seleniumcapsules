package com.bookstore;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

public enum BookStoreName implements Supplier<By> {

    RATINGS("miscDS.ratingsEnabled_CKBOX"),
    CONFIRM_EMAIL("miscDS.sendConfirmEmail_CKBOX");

    private final By by;

    private BookStoreName(String id) {
        this.by = id(id);
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