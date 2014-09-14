package com.algocrafts.selectors;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

/**
 * This enum is a Supplier of ById from Selenium By API.
 */
public enum Id implements Supplier<By> {

    MAIN("main"),
    MAIN_NAV("main-nav"),
    UI_DATEPICKER_DIV("ui-datepicker-div"),
    CONTENT("content"),
    EXTJS_DATEPICKER_TRIGGER("menuitem-1017-textEl"),
    EXTJS_CALENDAR("datemenu-1014"),
    EXTJS_CALENDAR_MONTH_YEAR("splitbutton-1020-btnInnerEl"),
    EXTJS_CALENDAR_PREV_MONTH("datepicker-1015-prevEl"),
    EXTJS_CALENDAR_NEXT_MONTH("datepicker-1015-nextEl"),

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