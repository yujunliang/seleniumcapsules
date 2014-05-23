package com.algocrafts.converters;


import com.algocrafts.pages.Locator;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Filter<T> implements Locator<Stream<T>, Stream<T>> {

    private final Predicate<T> predicate;

    public Filter(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public Stream<T> apply(Stream<T> elements) {
        return elements.filter(predicate);
    }

}
