package com.algocrafts.forms;

import com.algocrafts.pages.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.pages.Locators;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Function;
import java.util.function.Supplier;


public interface FormControl<Where extends Searchable<Where>> {

    /**
     * Check if the checkbox is checked by the given selector.
     *
     * @param selector selector
     * @return true if it is checked.
     */
    @SuppressWarnings("unchecked")
    default public boolean isChecked(Supplier<By> selector) {
        return new Checkbox<>((Where) this, selector).isChecked();
    }

    /**
     * Set checkbox to the given value.
     *
     * @param selector selector
     * @param value    value
     */
    @SuppressWarnings("unchecked")
    default public void check(Supplier<By> selector, boolean value) {
        new Checkbox<>((Where) this, selector).setValue(value);
    }

    /**
     * Read the value of the radio by given option.
     *
     * @param selector selector
     * @return the value of selected radio.
     */
    @SuppressWarnings("unchecked")
    default public String getRadio(Supplier<By> selector) {
        return new RadioButton<>((Where) this, selector).get();
    }

    /**
     * Read the value of the radio by given option.
     *
     * @param selector selector
     * @return the value of selected radio.
     */
    @SuppressWarnings("unchecked")
    default public <T extends Enum> T getRadio(Supplier<By> selector, Function<String, T> converter) {
        return converter.apply(getRadio(selector));
    }

    /**
     * Choose the radio by given option.
     *
     * @param selector selector
     * @param option   option
     */
    @SuppressWarnings("unchecked")
    default public void setRadio(Supplier<By> selector, Object option) {
        new RadioButton<>((Where) this, selector).setValue(option);
    }

    /**
     * Select the dropdown by given value.
     *
     * @param selector selector
     * @param value    value
     */
    @SuppressWarnings("unchecked")
    default public void select(Supplier<By> selector, Object value) {
        new Select<>((Where) this, Locators.<Where>select(selector)).selectByVisibleText(value);
    }

    /**
     * Read value from an input field.
     *
     * @param selector selector
     * @return its value.
     */
    @SuppressWarnings("unchecked")
    default public String get(Supplier<By> selector) {
        return new Input<>((Where) this, selector).get();
    }

    /**
     * Enter text into an input field.
     *
     * @param selector selector
     * @param value    value
     */
    @SuppressWarnings("unchecked")
    default public void put(Supplier<By> selector, Object value) {
        new Input<>((Where) this, selector).put(value);
    }

    /**
     * Autocomplete for text field and return the first found suggestion match the whole word.
     *
     * @param selector selector
     * @param value    value
     * @param locator  locator
     */
    @SuppressWarnings("unchecked")
    default public void autocomplete(Supplier<By> selector, Object value, Locator<Where, Element> locator) {
        new Input<>((Where) this, selector).autocomplete(value, locator);
    }

}
