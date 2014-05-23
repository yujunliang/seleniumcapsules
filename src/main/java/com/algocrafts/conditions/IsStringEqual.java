package com.algocrafts.conditions;

import java.util.function.Predicate;

public class IsStringEqual implements Predicate<String> {
    private final String string;

    public IsStringEqual(Object o) {
        this.string = o.toString();
    }

    @Override
    public boolean test(String input) {
        return input.equals(string);
    }
}