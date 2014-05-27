package com.algocrafts.forms;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

class Selection<Where extends Searchable<Where>> {

    private static final Logger log = getLogger(Selection.class);

    private final Where page;
    private final Locator<Where, Select> selectLocator;

    Selection(Where page, Locator<Where, Select> selectLocator) {
        this.page = page;
        this.selectLocator = selectLocator;
    }

    public void selectByVisibleText(Object text) {
        log.info("selecting select[" + selectLocator + "] using [" + text + "]");
        selectLocator.locate(page).selectByVisibleText(text.toString());
    }

    public boolean isMultiple() {
        return selectLocator.locate(page).isMultiple();
    }


    public Stream<Element> getOptions() {
        return selectLocator.locate(page).getOptions().stream().map(Element::new);
    }

    public Stream<Element> getAllSelectedOptions() {
        return selectLocator.locate(page).getAllSelectedOptions().stream().map(Element::new);
    }

    public Element getFirstSelectedOption() {
        return new Element(selectLocator.locate(page).getFirstSelectedOption());
    }

    public void selectByIndex(int index) {
        selectLocator.locate(page).selectByIndex(index);
    }

    public void selectByValue(Object value) {
        selectLocator.locate(page).selectByValue(value.toString());
    }

    public void deselectAll() {
        selectLocator.locate(page).deselectAll();
    }

    public void deselectByValue(Object value) {
        selectLocator.locate(page).deselectByValue(value.toString());
    }

    public void deselectByIndex(int index) {
        selectLocator.locate(page).deselectByIndex(index);
    }

    public void deselectByVisibleText(Object text) {
        selectLocator.locate(page).deselectByVisibleText(text.toString());
    }
}
