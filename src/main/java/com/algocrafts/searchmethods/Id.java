package com.algocrafts.searchmethods;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

public enum Id implements Supplier<By> {

    MAIN_NAV("main-nav"),
    UI_DATEPICKER_DIV("ui-datepicker-div"),
    CONTENT("content"),
    DATE_PICKER("datepicker"),

    TRY_UI_DATEPICKER_DIV("ui-datepicker-div"),
    TRY_CONTENT("content"),
    TRY_YS_CART("ys_cart"),
    TRY_DATE_PICKER("datepicker");

    private final By by;

    private Id(String id) {
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