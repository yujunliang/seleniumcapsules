package com.algocrafts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.slf4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.transform;
import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

public class Element implements Searchable<Element>, WebElement, Locatable {

    private static final Logger logger = getLogger(Element.class);

    private final WebElement element;

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

    @Override
    public Stream<Element> getElements(By by) {
        return element.findElements(by).stream().map(Element::new);
    }

    @Deprecated
    @Override
    public List<WebElement> findElements(By by) {
        return getElements(by).collect(toList());
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
    public Element findElement(By by) {
        try {
            logger.info("Seeking [{}]", by);
            return getElement(by);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Element untilFindElement(final By by) {
        logger.info("Seeking [{}]", by);
        return until((Element element) -> getElement(by));
    }

    @Override
    public void save() {
        logger.info("Saving " + element + "[" + this + "]");
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public String toString() {
        String tagName = element.getTagName();
        return tagName.equals("input") ?
            element.getAttribute("value") : tagName.equals("img") ?
            element.getAttribute("src") : element.getText();
    }

    private Element getElement(By by) {
        Element nested = new Element(element.findElement(by));
        logger.info("Found [{}]", nested);
        return nested;
    }

}
