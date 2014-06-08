package com.algocrafts.forms;


import com.algocrafts.algorithm.Retry;
import com.algocrafts.conditions.Equals;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locating;
import com.algocrafts.selenium.Locator;
import com.algocrafts.locators.Locators;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static com.algocrafts.converters.GetText.VALUE;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.slf4j.LoggerFactory.getLogger;

public class Input<Where extends Searchable<Where>> extends Locating<Where, Element> {

    public static final Logger log = getLogger(Input.class);

    /**
     * Constructor of the input field.
     *
     * @param where    where
     * @param selector selector
     */
    public Input(Where where, Supplier<By> selector) {
        super(where, Locators.<Where>tryElement(selector));
    }

    /**
     * the value of input field, for example, "good" will be return
     * <p>
     * String value = page.get(() -&gt; By.name("status"))
     * <p>
     * &lt;input name="status" value="good"/&gt;
     *
     * @return the value of the input
     */
    public String getValue() {
        final Retry retry = new Retry(5, 1, SECONDS);
        try {
            retry.attempt(() -> {
                log.info("{}", retry);
                Element element = locate();
                return VALUE.locate(element);
            });
        } catch (Exception e) {
            log.info("Failed to read text", e);
        }
        return null;
    }

    /**
     * set the value of input field, for example,
     * <p>
     * after,
     * page.set(() -&gt; By.name("status"), "good");
     * <p>
     * it will be,
     * &lt;input name="status" value="good"/&gt;
     *
     * @param value the value to set
     */

    public void put(final Object value) {
        String string = value.toString();
        final Retry retry = new Retry(5, 1, SECONDS);
        try {
            retry.attempt(() -> {
                log.info("{}", retry);
                Element element = locate();
                element.clear();
                element.sendKeys(string);
                if (VALUE.and(new Equals(string)).test(element)) {
                    retry.off();
                }
                return null;

            });
        } catch (Exception e) {
            log.info("Failed to set text {}", string);
        }
    }

    /**
     * Test the autocomplete function for the input by given value, click the element
     * on the suggestion list which matches value parameter.
     * <p>
     * Please refer "http://seleniumcapsules.blogspot.com/2014/05/by-xpath.html"
     *
     * @param value   value
     * @param locator locator
     */
    public void autocomplete(Object value, Locator<Where, Element> locator) {
        Element element = locate();
        element.clear();
        Element suggestion;
        for (char c : value.toString().toCharArray()) {
            element.sendKeys(String.valueOf(c));
            suggestion = locator.locate(where);
            if (suggestion != null) {
                suggestion.click();
                return;
            }
        }
        suggestion = where.until(locator);
        if (suggestion != null) {
            suggestion.click();
        }
    }
}
