package com.algocrafts.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class ElementFinder implements Locator<SearchContext, Element> {

    public static final Logger logger = getLogger(ElementFinder.class);

    private final By by;

    public ElementFinder(By by) {
        this.by = by;
    }

    @Override
    public Element locate(SearchContext t) {
        logger.info("Seeking [{}]", by);
        WebElement element = t.findElement(by);
        logger.info("Found [{}]", element);
        Element element1 = new Element(element);
        element1.setBy(by);
        element1.setBrowser(t);
        return element1;
    }
}
