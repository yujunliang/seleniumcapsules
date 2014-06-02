package com.algocrafts.table;


import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locators;
import com.algocrafts.selenium.Locator;

import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.pages.Locators.elements;
import static com.algocrafts.selectors.TagName.*;

public class Table<T> {

    private final AbstractPage page;
    private final Locator<AbstractPage, Element> locator;
    private Locator<Stream<Element>, T> mapper;

    public Table(AbstractPage page,
                 Locator<AbstractPage, Element> locator,
                 Locator<Stream<Element>, T> mapper) {
        this.page = page;
        this.locator = locator;
        this.mapper = mapper;
    }

    public Stream<String> getHeader() {
        return locator.and(elements(TH)).locate(page).map(TEXT);
    }

    public Stream<T> getRows() {
        return locator.and(elements(TR)).locate(page).map(Locators.<Element>elements(TD).and(mapper));
    }

}
