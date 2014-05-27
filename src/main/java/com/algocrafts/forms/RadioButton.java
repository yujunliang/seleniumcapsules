package com.algocrafts.forms;


import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.converters.Filter;
import com.algocrafts.converters.FirstItem;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.converters.ElementFunctions.CLICK_IF_NOT_NULL;
import static com.algocrafts.converters.GetText.VALUE;
import static com.algocrafts.pages.Locators.elements;

class RadioButton<Where extends Searchable<Where>> {

    private final Where page;
    private final Locator<Where, Stream<Element>> radioButtonGroup;

    public RadioButton(Where page, Supplier<By> method) {
        this.page = page;
        this.radioButtonGroup = elements(method);
    }

    public void setValue(Object value) {
        radioButtonGroup
                .and(new Filter<>(DISPLAYED.and(VALUE.and(new IsStringEqual(value)))))
                .and(new FirstItem<>())
                .and(CLICK_IF_NOT_NULL)
                .locate(page);
    }
}