package com.algocrafts.conditions;


import java.util.function.Predicate;

public class StringContains implements Predicate<String> {
    private final String string;

    public StringContains(String string) {
        this.string = string;
    }

    @Override
    public boolean test(String input) {
        return input.contains(string);
    }
}