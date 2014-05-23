package com.algocrafts.searchmethods;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.partialLinkText;

public enum PartialLinkText implements Supplier<By> {

    UI_DATEPICKER_CLOSE("ui-datepicker-close");

    private final By by;

    private PartialLinkText(String id) {
        this.by = partialLinkText(id);
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
