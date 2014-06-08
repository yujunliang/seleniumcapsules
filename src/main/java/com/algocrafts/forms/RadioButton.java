package com.algocrafts.forms;


import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locating;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.StringEquals.TRUE;
import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.converters.GetText.CHECKED;
import static com.algocrafts.converters.GetText.VALUE;
import static com.algocrafts.locators.Locators.elements;

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
        new FirstMatch<>(DISPLAYED.and(VALUE.and(new Equals(value)))).and(CLICK).locate(locate());
    }

    /**
     * @return the value of the select radio
     */
    public String getValue() {
        return new FirstMatch<>(DISPLAYED.and(CHECKED.and(TRUE))).and(VALUE).locate(locate());
    }
}

