package com.algocrafts.locators;


import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ElementsLocator<Where extends Searchable<Where>>
    extends AbstractLocator<Where, Stream<Element>> {

    public ElementsLocator(Supplier<By> method) {
        super(method.get());
    }

    @Override
    public Stream<Element> find(Where where) {
        return where.getElements(by);
    }
}
