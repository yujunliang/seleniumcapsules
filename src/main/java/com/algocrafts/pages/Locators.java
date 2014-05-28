package com.algocrafts.pages;

import com.algocrafts.conditions.HasElements;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.selectors.TagName.OPTION;
import static org.slf4j.LoggerFactory.getLogger;

public class Locators<Where extends Searchable<Where>, What>
        implements Locator<Where, What> {

    public static <Where extends Searchable<Where>> Locators<Where, Element> element(Supplier<By> selector) {
        return new Locators<>((Where where) -> where.untilFound(selector.get()));
    }

    public static <Where extends Searchable<Where>> Locators<Where, Stream<Element>> elements(Supplier<By> selector) {
        return new Locators<>((Where where) -> where.findElements(selector));
    }

    public static <Where extends Searchable<Where>> Locators<Where, Element> tryElement(Supplier<By> selector) {
        return new Locators<>((Where where) -> where.findElement(selector.get()));
    }

    public static <Where extends Searchable<Where>> Locators<Where, Select> select(Supplier<By> selector) {
        return new Locators<>((Where where) -> {
            final Element element = where.untilFound(selector.get());
            try {
                element.until(Locators.<Element>elements(OPTION).and(new HasElements<>()));
                return new Select(element);
            } catch (NoSuchElementException e) {
                element.click();
                where.save();
                log.error("Timeout waiting for the option list to populate.", e);
                throw e;
            }
        });
    }

    private static final Logger log = getLogger(Locators.class);

    private final Locator<Where, What> locator;

    public Locators(Locator<Where, What> locator) {
        this.locator = locator;
    }

    @Override
    public What locate(Where where) {
        return locator.locate(where);
    }
}