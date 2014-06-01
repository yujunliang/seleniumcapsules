package com.algocrafts.forms;

import com.algocrafts.pages.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

public class Selection<Where extends Searchable<Where>> {

    private static final Logger log = getLogger(Selection.class);

    private final Where where;
    private final Locator<Where, Select> locator;

    /**
     * Constructor of the Select, It is a wrapper for the Select from Selenium UI.
     * @param where
     * @param locator
     */
    Selection(Where where, Locator<Where, Select> locator) {
        this.where = where;
        this.locator = locator;
    }

    public void selectByVisibleText(Object text) {
        log.info("selecting select[" + locator + "] using [" + text + "]");
        locator.locate(where).selectByVisibleText(text.toString());
    }

    public boolean isMultiple() {
        return locator.locate(where).isMultiple();
    }


    public Stream<Element> getOptions() {
        return locator.locate(where).getOptions().stream().map(Element::new);
    }

    public Stream<Element> getAllSelectedOptions() {
        return locator.locate(where).getAllSelectedOptions().stream().map(Element::new);
    }

    public Element getFirstSelectedOption() {
        return new Element(locator.locate(where).getFirstSelectedOption());
    }

    public void selectByIndex(int index) {
        locator.locate(where).selectByIndex(index);
    }

    public void selectByValue(Object value) {
        locator.locate(where).selectByValue(value.toString());
    }

    public void deselectAll() {
        locator.locate(where).deselectAll();
    }

    public void deselectByValue(Object value) {
        locator.locate(where).deselectByValue(value.toString());
    }

    public void deselectByIndex(int index) {
        locator.locate(where).deselectByIndex(index);
    }

    public void deselectByVisibleText(Object text) {
        locator.locate(where).deselectByVisibleText(text.toString());
    }
}
