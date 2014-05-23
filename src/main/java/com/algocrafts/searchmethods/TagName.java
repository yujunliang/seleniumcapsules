package com.algocrafts.searchmethods;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.tagName;

public enum TagName implements Supplier<By> {
    A("a"),
    EM("em"),
    H1("h1"),
    H5("h5"),
    I("i"),
    P("p"),
    DIV("div"),
    IMG("img"),
    INPUT("input"),
    LI("li"),
    OPTION("option"),
    TABLE("table"),
    TD("td"),
    TR("tr"),
    TH("th"),
    UL("ul");

    private final By by;

    private TagName(String id) {
        this.by = tagName(id);
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
