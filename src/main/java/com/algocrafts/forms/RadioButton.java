package com.algocrafts.forms;


import com.algocrafts.conditions.StringContains;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.converters.OptionalGetter;
import com.algocrafts.locators.Locators;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locating;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_NULL;
import static com.algocrafts.conditions.StringEquals.TRUE;
import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.converters.GetText.CHECKED;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.Xpath.PARENT;

public class RadioButton<Where extends Searchable<Where>> extends Locating<Where, Stream<Element>> {

    /**
     * Constructor this radio button.
     *
     * @param where    where
     * @param selector selector
     */
    public RadioButton(Where where, Supplier<By> selector) {
        super(where, elements(selector));
    }

    /**
     * @param value value to set
     */
    public void setValue(Object value) {
        locate(new FirstMatch<>(NOT_NULL.and(DISPLAYED).and(Locators.<Element>element(PARENT).and(TEXT).and(new StringContains(value.toString())))).and(GET).and(CLICK));
    }

    /**
     * @return the value of the select radio
     */
    public String getValue() {
        return locate(new FirstMatch<>(NOT_NULL.and(DISPLAYED).and(CHECKED.and(TRUE))).and(GET).and(element(PARENT)).and(TEXT));
    }
}

