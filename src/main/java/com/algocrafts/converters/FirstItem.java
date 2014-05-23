package com.algocrafts.converters;


import com.algocrafts.pages.Locator;

import java.util.stream.Stream;

public class FirstItem<T> implements Locator<Stream<T>, T> {

    @Override
    public T apply(Stream<T> elements) {
        return elements.findFirst().get();
    }
}
