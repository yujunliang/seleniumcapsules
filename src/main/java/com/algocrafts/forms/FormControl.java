package com.algocrafts.forms;

import com.algocrafts.pages.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.pages.Locators;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;


public interface FormControl<Where extends Searchable<Where>> {

    /**
     * Set checkbox to the given value.
     *
     * @param selector
     * @param value
     */
    @SuppressWarnings("unchecked")
    default public void check(Supplier<By> selector, boolean value) {
        new Checkbox<>((Where) this, selector).setValue(value);
    }

    /**
     * Choose the radio by given option.
     *
     * @param selector
     * @param option
     */
    @SuppressWarnings("unchecked")
    default public void radio(Supplier<By> selector, Object option) {
        new RadioButton<>((Where) this, selector).setValue(option);
    }

    /**
     * Select the dropdown by given value.
     *
     * @param selector
     * @param value
     */
    @SuppressWarnings("unchecked")
    default public void select(Supplier<By> selector, Object value) {
        new Selection<>((Where) this, Locators.<Where>select(selector)).selectByVisibleText(value);
    }

    /**
     * Enter text into an input filed.
     *
     * @param selector
     * @param value
     */
    @SuppressWarnings("unchecked")
    default public void put(Supplier<By> selector, Object value) {
        new Input<>((Where) this).put(selector, value);
    }

    /**
     * Autocomplete for text field and return the first found suggestion match the whole word.
     *
     * @param selector
     * @param value
     * @param locator
     */
    @SuppressWarnings("unchecked")
    default public void autocomplete(Supplier<By> selector, Object value, Locator<Where, Element> locator) {
        new Input<>((Where) this).autocomplete(selector, value, locator);
    }

}
