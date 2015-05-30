package com.algocrafts.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class Element implements SearchScope<Element>, WebElement, Locatable {

    private static final Logger logger = getLogger(Element.class);

    private WebElement element;
    private SearchContext searchContext;
    private By by;

    public Element(WebElement element) {
        this.element = element;
    }

    @Override
    public String getAttribute(String key) {
        return element.getAttribute(key);
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    @Override
    public void click() {
        logger.info("clicking " + element + "[" + this + "]");
        element.click();
    }

    @Override
    public void submit() {
        logger.info("Submitting " + element + "[" + this + "]");
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Deprecated
    @Override
    public List<WebElement> findElements(By by) {
        return new ElementsFinder(by).locate(element);
    }

    @Deprecated
    @Override
    public Element findElement(By by) {
        Element locate;
        try {
            locate = new ElementFinder(by).locate(element);   //<1>
        } catch (StaleElementReferenceException e) {          //<2>
            this.element = searchContext.findElement(this.by);  //<3>
            return findElement(by);                             //<4>
        }
        return locate;
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public void onTimeout() {
        logger.info("Saving " + element + "[" + this + "]");
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public String toString() {
        String tagName = element.getTagName();
        return "[Element: " + (tagName.equals("input") ?
                element.getAttribute("value") : tagName.equals("img") ?
                element.getAttribute("src") : element.getText()) + "] wrapping " + element;
    }

    public void setBrowser(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public void setBy(By by) {
        this.by = by;
    }
}
