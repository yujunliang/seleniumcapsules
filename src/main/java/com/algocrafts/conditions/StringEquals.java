package com.algocrafts.conditions;

import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Locator;

import java.util.function.Predicate;

public enum StringEquals implements Predicate<String> {

    TRUE("true"),
    YAHOO("Copyright © 2014 Yahoo Inc. All rights reserved."),
    MANNING("© 2003-2014 Manning Publications Co."),
    DATEPICKER("Datepicker");


    private final String text;

    private StringEquals(String text) {
        this.text = text;
    }

    @Override
    public boolean test(String element) {
        return element.equals(text);
    }

    public static class FrameLocator implements Locator<AbstractPage, AbstractPage> {

        private final int index;

        public FrameLocator(int index) {
            this.index = index;
        }

        @Override
        public AbstractPage locate(AbstractPage page) {
            return page.frame(index);
        }

        @Override
        public String toString() {
            return "frames[" + index + "]";
        }
    }
}