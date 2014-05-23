package com.algocrafts.forms;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import com.algocrafts.locators.SelectLocator;
import org.slf4j.Logger;

import java.util.List;

import static com.google.common.collect.Lists.transform;
import static org.slf4j.LoggerFactory.getLogger;

class Select<Where extends Searchable<Where>> {

    private static final Logger log = getLogger(Select.class);

    private final Where page;
    private final SelectLocator<Where> selectLocator;

    Select(Where page, String name) {
        this(page, new SelectLocator<>(name));
    }

    Select(Where page, SelectLocator<Where> selectLocator) {
        this.page = page;
        this.selectLocator = selectLocator;
    }

    public void selectByVisibleText(Object text) {
        log.info("selecting select[" + selectLocator + "] using [" + text + "]");
        selectLocator.apply(page).selectByVisibleText(text.toString());
    }

    public boolean isMultiple() {
        return selectLocator.apply(page).isMultiple();
    }


    public List<Element> getOptions() {
        return transform(selectLocator.apply(page).getOptions(), Element::new);
    }

    public List<Element> getAllSelectedOptions() {
        return transform(selectLocator.apply(page).getAllSelectedOptions(), Element::new);
    }

    public Element getFirstSelectedOption() {
        return new Element(selectLocator.apply(page).getFirstSelectedOption());
    }

    public void selectByIndex(int index) {
        selectLocator.apply(page).selectByIndex(index);
    }

    public void selectByValue(Object value) {
        selectLocator.apply(page).selectByValue(value.toString());
    }

    public void deselectAll() {
        selectLocator.apply(page).deselectAll();
    }

    public void deselectByValue(Object value) {
        selectLocator.apply(page).deselectByValue(value.toString());
    }

    public void deselectByIndex(int index) {
        selectLocator.apply(page).deselectByIndex(index);
    }

    public void deselectByVisibleText(Object text) {
        selectLocator.apply(page).deselectByVisibleText(text.toString());
    }
}
