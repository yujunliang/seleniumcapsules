package com.algocrafts.converters;

import com.algocrafts.selenium.Locator;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class FirstMatch<T> implements Locator<Stream<T>, T> {

    private final Predicate<T> predicate;

    public FirstMatch(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public T locate(Stream<T> stream) {
        return new Filter<>(predicate).and(new FirstItem<>()).locate(stream);
    }
}
