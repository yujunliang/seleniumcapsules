package com.algocrafts.converters;


import com.algocrafts.pages.Locator;

import java.util.List;

public class ElementAtIndex<T> implements Locator<List<T>, T> {

    private final int index;

    public ElementAtIndex(int index) {
        this.index = index;
    }

    @Override
    public T apply(List<T> list) {
        return list.get(index);
    }

}
