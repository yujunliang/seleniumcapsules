package com.algocrafts.forms;

import com.algocrafts.locators.SelectLocator;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locating;
import com.algocrafts.selenium.Searchable;
import org.slf4j.Logger;

import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

public class Select<Where extends Searchable<Where>> extends Locating<Where, org.openqa.selenium.support.ui.Select> {

    private static final Logger log = getLogger(Select.class);

    /**
     * Constructor of the Select, It is a wrapper for the Select from Selenium UI.
     * @param where
     * @param locator
     */
    public Select(Where where, SelectLocator<Where> locator) {
        super(where, locator);
    }

    public void selectByVisibleText(Object text) {
        log.info("selecting select[" + locator + "] using [" + text + "]");
        get().selectByVisibleText(text.toString());
    }

    public boolean isMultiple() {
        return get().isMultiple();
    }


    public Stream<Element> getOptions() {
        return get().getOptions().stream().map(Element::new);
    }

    public Stream<Element> getAllSelectedOptions() {
        return get().getAllSelectedOptions().stream().map(Element::new);
    }

    public Element getFirstSelectedOption() {
        return new Element(get().getFirstSelectedOption());
    }

    public void selectByIndex(int index) {
        get().selectByIndex(index);
    }

    public void selectByValue(Object value) {
        get().selectByValue(value.toString());
    }

    public void deselectAll() {
        get().deselectAll();
    }

    public void deselectByValue(Object value) {
        get().deselectByValue(value.toString());
    }

    public void deselectByIndex(int index) {
        get().deselectByIndex(index);
    }

    public void deselectByVisibleText(Object text) {
        get().deselectByVisibleText(text.toString());
    }
}
