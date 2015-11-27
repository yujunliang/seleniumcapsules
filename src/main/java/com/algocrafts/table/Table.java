package com.algocrafts.table;


import com.algocrafts.locators.Locators;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.TagName.*;
import static java.util.stream.Collectors.toSet;

public class Table<T, Where extends SearchScope<Where>> {


    private final Where where;
    private final Locator<Where, Element> locator;
    private final Locator<Stream<Element>, T> mapper;

    public Table(Where where,
                 Locator<Where, Element> locator,
                 Locator<Stream<Element>, T> mapper) {
        this.where = where;
        this.locator = locator;
        this.mapper = mapper;
    }

    public Stream<String> getHeader() {
        return locator.andThen(elements(TH)).locate(where).map(TEXT);
    }

    public Stream<T> getRows() {
        return locator.andThen(elements(TR)).locate(where)
                .filter(e ->
                        Locators.<Element>optionalElement(TD)
                                .locate(e)
                                .isPresent())
                .map(elements(TD))
                .map(mapper);
    }

    public TableContents<T> getContents() {
        return new TableContents<T>(
                this.getHeader().collect(toSet()),
                this.getRows().collect(Collectors.<T>toSet())
        );
    }
}

