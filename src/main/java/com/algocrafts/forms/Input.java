package com.algocrafts.forms;


import com.algocrafts.algorithm.Retry;
import com.algocrafts.conditions.Equals;
import com.algocrafts.conditions.StringContains;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.Locators;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locating;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.GetText.VALUE;
import static com.algocrafts.converters.OptionalGetter.GET;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.slf4j.LoggerFactory.getLogger;

public class Input<Where extends SearchScope<Where>> extends Locating<Where, Optional<Element>> {

    public static final Logger log = getLogger(Input.class);

    /**
     * Constructor of the input field.
     *
     * @param where    where
     * @param selector selector
     */
    public Input(Where where, Supplier<By> selector) {
        super(where, Locators.<Where>optionalElement(selector));
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
            retry.attempt(() -> locate(GET.andThen(VALUE)));
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
                Element element = locate(GET);
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
    public void autocomplete(Object value, Locator<Where, Stream<Element>> locator) {
        Element element = locate(GET);
        element.clear();
        for (char c : value.toString().toCharArray()) {
            element.sendKeys(String.valueOf(c));
            Stream<Element> elements = use(locator);
            Optional<Element> locate = new FirstMatch<>(TEXT.and(new StringContains(value.toString()))).locate(elements);
            if (locate.isPresent()) {
                locate.get().click();
            }
        }
        FluentWait<Input<Where>> ignoring = new FluentWait<>(this)
                .pollingEvery(5, MILLISECONDS)
                .ignoring(Exception.class);
        ignoring.until((Input i) -> {
            Stream<Element> elements = use(locator);
            Optional<Element> locate = new FirstMatch<>(TEXT.and(new StringContains(value.toString()))).locate(elements);
            return locate.get();
        }).click();


    }
}
