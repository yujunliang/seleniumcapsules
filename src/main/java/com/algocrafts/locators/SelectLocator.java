package com.algocrafts.locators;

import com.algocrafts.conditions.HasElements;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static com.algocrafts.selectors.TagName.OPTION;
import static org.slf4j.LoggerFactory.getLogger;


public class SelectLocator<Where extends Searchable<Where>>
        extends BaseLocator<Where, Select> {

    private static final Logger log = getLogger(SelectLocator.class);

    public SelectLocator(Supplier<By> selector) {
        super((Where where) -> {
            final Element element = where.untilFound(selector.get());
            try {
                element.until(new ElementsLocator<Element>(OPTION).and(new HasElements<>()));
                return new Select(element);
            } catch (NoSuchElementException e) {
                element.click();
                where.save();
                log.error("Timeout waiting for the option list to populate.", e);
                throw e;
            }
        });
    }
}
