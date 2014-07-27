package com.algocrafts.converters;


import com.algocrafts.selenium.Locator;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class FirstItem<T> implements Locator<Stream<T>, Optional<T>> {

    @Override
    public Optional<T> locate(Stream<T> elements) {
        try {
            return elements.findFirst();
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
}
