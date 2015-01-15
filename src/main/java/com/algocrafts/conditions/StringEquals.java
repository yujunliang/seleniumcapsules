package com.algocrafts.conditions;

import java.util.function.Predicate;

public enum StringEquals implements Predicate<String> {

    TRUE("true"),
    YAHOO("Copyright © 2015 Yahoo Inc. All rights reserved."),
    MANNING("© 2003-2015 Manning Publications Co."),
    DATEPICKER("Datepicker");

    private final String text;

    private StringEquals(String text) {
        this.text = text;
    }

    @Override
    public boolean test(String element) {
        return text.equals(element);
    }

}