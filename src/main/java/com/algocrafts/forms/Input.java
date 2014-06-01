package com.algocrafts.forms;


import com.algocrafts.algorithm.Retry;
import com.algocrafts.pages.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.pages.Locators;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static com.algocrafts.converters.GetText.VALUE;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.slf4j.LoggerFactory.getLogger;

class Input<Where extends Searchable<Where>> implements Supplier<String> {

    public static final Logger log = getLogger(Input.class);

    private final Where where;
    private final Supplier<By> selector;

    /**
     * Constructor of the input field.
     *
     * @param where
     * @param selector
     */
    Input(Where where, Supplier<By> selector) {
        this.where = where;
        this.selector = selector;
    }

    /**
     * the value of input field, for example, "good" will be return
     * <p>
     * String value = page.get(() -> By.name("status"))
     * <p>
     * <input name="status" value="good"/>
     *
     * @return the value of the input
     */
    public String get() {
        log.info("reading input[{}]]", selector);
        final Retry retry = new Retry(5, 1, SECONDS);
        try {
            retry.attempt(() -> {
                log.info("{}", retry);
                Element element = Locators.<Where>tryElement(selector).locate(where);
                return VALUE.locate(element);
            });
        } catch (Exception e) {
            log.info("Failed to read text from {}", selector);
        }
        return null;
    }

    /**
     * set the value of input field, for example,
     * <p>
     * after,
     * page.set(() -> By.name("status"), "good");
     * <p>
     * it will be,
     * <input name="status" value="good"/>
     */
    public void put(final Object value) {
        String string = value.toString();
        log.info("setting input[{}]=[{}]", selector, string);
        final Retry retry = new Retry(5, 1, SECONDS);
        try {
            retry.attempt(() -> {
                log.info("{}", retry);
                Element element = Locators.<Where>tryElement(selector).locate(where);
                element.clear();
                element.sendKeys(string);
                if (VALUE.locate(element).equals(string)) {
                    retry.off();
                }
                return null;

            });
        } catch (Exception e) {
            log.info("Failed to set text {} to {}", string, selector);
        }
    }

    /**
     * Test the autocomplete function for the input by given selector, click the element
     * on the suggestion list which has the same value of value parameter.
     *
     * @param value
     * @param locator
     * @see http://seleniumcapsules.blogspot.com/2014/05/by-xpath.html
     */
    public void autocomplete(Object value, Locator<Where, Element> locator) {
        Element apply = Locators.<Where>element(selector).locate(where);
        apply.clear();
        for (char c : value.toString().toCharArray()) {
            apply.sendKeys(String.valueOf(c));
            Element apply1 = locator.locate(where);
            if (apply1 != null) {
                apply1.click();
                return;
            }
        }
        Element element = where.until(locator);
        if (element != null) {
            element.click();
        }
    }

}
