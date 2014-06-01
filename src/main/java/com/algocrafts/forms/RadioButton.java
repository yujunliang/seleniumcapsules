package com.algocrafts.forms;


import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.converters.GetText;
import com.algocrafts.pages.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.StringEquals.TRUE;
import static com.algocrafts.converters.ElementFunctions.CLICK_IF_NOT_NULL;
import static com.algocrafts.converters.GetText.CHECKED;
import static com.algocrafts.converters.GetText.VALUE;
import static com.algocrafts.pages.Locators.elements;
import static java.util.function.Predicate.isEqual;

public class RadioButton<Where extends Searchable<Where>> {

    private final Where where;
    private final Locator<Where, Stream<Element>> radioButtonGroup;

    /**
     * Constructor this radio button.
     *
     * @param where
     * @param selector
     */
    public RadioButton(Where where, Supplier<By> selector) {
        this.where = where;
        this.radioButtonGroup = elements(selector);
    }

    public void setValue(Object value) {
        radioButtonGroup
                .and(new FirstMatch<>(DISPLAYED.and(VALUE.and(new IsStringEqual(value)))))
                .and(CLICK_IF_NOT_NULL)
                .locate(where);
    }

    public String get() {
        return radioButtonGroup
                .and(new FirstMatch<>(DISPLAYED.and(CHECKED.and(TRUE))))
                .and(GetText.VALUE)
                .locate(where);
    }
}