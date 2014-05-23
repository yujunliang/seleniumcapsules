package com.algocrafts.locators;

import com.algocrafts.conditions.HasElements;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import static com.algocrafts.searchmethods.TagName.OPTION;
import static org.openqa.selenium.By.name;
import static org.slf4j.LoggerFactory.getLogger;


public class SelectLocator<Where extends Searchable<Where>> implements Locator<Where, Select> {

    private static final Logger log = getLogger(SelectLocator.class);

    private final Locator<Where, Element> locator;

    public SelectLocator(final String name) {
        this(new ElementLocator<>(name(name)));
    }

    public SelectLocator(final Locator<Where, Element> locator) {
        this.locator = locator;
    }

    public Select apply(Where where) {
        final Element element = locator.apply(where);
        try {
            element.until(new ElementsLocator<Element>(OPTION).and(new HasElements<>()));
            return new Select(element);
        } catch (TimeoutException e) {
            element.click();
            where.save();
            log.error("Timeout waiting for the option list to populate.", e);
            throw e;
        }
    }

    @Override
    public String toString() {
        return locator.toString();
    }
}
