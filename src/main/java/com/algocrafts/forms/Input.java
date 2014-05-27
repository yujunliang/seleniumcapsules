package com.algocrafts.forms;


import com.algocrafts.algorithm.Retry;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.locators.ElementTryLocator;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static com.algocrafts.converters.GetText.VALUE;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.slf4j.LoggerFactory.getLogger;

class Input<Where extends Searchable<Where>> {

    public static final Logger log = getLogger(Input.class);

    private final Where where;

    Input(Where where) {
        this.where = where;
    }

    public void put(Supplier<By> method, final Object value) {
        String string = value.toString();
        log.info("setting input[{}]=[{}]", method, string);
        final Retry retry = new Retry(5, 1, SECONDS);
        try {
            retry.attempt(() -> {
                log.info("{}", retry);
                Element element = new ElementTryLocator<Where>(method).locate(where);
                element.clear();
                element.sendKeys(string);
                if (VALUE.locate(element).equals(string)) {
                    retry.off();
                }
                return null;

            });
        } catch (Exception e) {
            log.info("Failed to set text {} to {}", string, method);
        }
    }

    public void autocomplete(Supplier<By> method, Object value, Locator<Where, Element> condition) {
        Element apply = new ElementLocator<Where>(method).locate(where);
        apply.clear();
        for (char c : value.toString().toCharArray()) {
            apply.sendKeys(String.valueOf(c));
            Element apply1 = condition.locate(where);
            if (apply1 != null) {
                apply1.click();
                return;
            }
        }
        Element element = where.until(condition);
        if (element!= null) {
            element.click();
        }
    }

}
