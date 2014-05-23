package com.algocrafts.conditions;


import java.util.function.Predicate;
import java.util.stream.Stream;

public class HasElements<T> implements Predicate<Stream<T>> {

    @Override
    public boolean test(Stream<T> stream) {
        return stream.count() > 0;
    }

}
