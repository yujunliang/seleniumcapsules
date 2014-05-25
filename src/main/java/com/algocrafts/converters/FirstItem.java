package com.algocrafts.converters;


import com.algocrafts.pages.Locator;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class FirstItem<T> implements Locator<Stream<T>, T> {

    @Override
    public T apply(Stream<T> elements) {
        try {
            return elements.findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
