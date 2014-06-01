package com.algocrafts.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class ElementsFinder implements Locator<SearchContext, List<WebElement>>{
    public static final Logger logger = getLogger(ElementsFinder.class);
    private By by;

    public ElementsFinder(By by) {
        this.by = by;
    }

    @Override
    public List<WebElement> locate(SearchContext t) {
        logger.info("Seeking elements [{}]", by);
        List<WebElement> elements = t.findElements(by);
        logger.info("Found [{}]", elements);
        return elements;
    }
}
