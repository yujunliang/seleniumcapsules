package com.algocrafts.forms;


import com.algocrafts.algorithm.Retry;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.locators.ElementTryLocator;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.function.Predicate;
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
                Element element = new ElementTryLocator<Where>(method).apply(where);
                element.clear();
                element.sendKeys(string);
                if (VALUE.apply(element).equals(string)) {
                    retry.off();
                }
                return null;

            });
        } catch (Exception e) {
            log.info("Failed to set text {} to {}", string, method);
        }
    }

    public void autoComplete(Supplier<By> method, Object value, Predicate<Where> condition) {
        new ElementLocator<Where>(method).apply((Where) this).clear();
        for (char c : value.toString().toCharArray()) {
            new ElementLocator<Where>(method).apply((Where) this).sendKeys(String.valueOf(c));
            if (condition.test((Where) this)) {
                break;
            }
        }
        ((Where) this).until(condition);
    }

}
