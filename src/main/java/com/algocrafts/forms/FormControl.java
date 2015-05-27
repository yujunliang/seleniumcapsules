package com.algocrafts.forms;

import com.algocrafts.locators.Locators;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import java.io.File;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;


public interface FormControl<T extends SearchScope<T>> extends SearchScope<T> {

    /**
     * Check if the checkbox is checked by the given selector.
     *
     * @param selector selector
     * @return true if it is checked.
     */
    @SuppressWarnings("unchecked")
    default boolean isChecked(Supplier<By> selector) {
        return new Checkbox<>((T) this, selector).isChecked();
    }

    /**
     * Set checkbox to the given value.
     *
     * @param selector selector
     * @param value    value
     */
    @SuppressWarnings("unchecked")
    default void setCheckbox(Supplier<By> selector, boolean value) {
        new Checkbox<>((T) this, selector).setValue(value);
    }

    /**
     * Read the value of the radio by given option.
     *
     * @param selector selector
     * @return the value of selected radio.
     */
    @SuppressWarnings("unchecked")
    default String getRadio(Supplier<By> selector) {
        return new RadioButton<>((T) this, selector).getValue();
    }

    /**
     * Read the value of the radio by given option.
     *
     * @param selector selector
     * @return the value of selected radio.
     */
    @SuppressWarnings("unchecked")
    default <T extends Enum> T getRadio(Supplier<By> selector, Function<String, T> converter) {
        return converter.apply(getRadio(selector));
    }

    /**
     * Choose the radio by given option.
     *
     * @param selector selector
     * @param option   option
     */
    @SuppressWarnings("unchecked")
    default void setRadioButton(Supplier<By> selector, Object option) {
        new RadioButton<>((T) this, selector).setValue(option);
    }

    /**
     * Select the dropdown by given value.
     *
     * @param selector selector
     * @param value    value
     */
    @SuppressWarnings("unchecked")
    default void select(Supplier<By> selector, Object value) {
        new ForwardingSelect<>((T) this, Locators.<T>select(selector)).selectByVisibleText(value);
    }

    /**
     * Read value from an input field, convert to other type by converter.
     *
     * @param selector  selector
     * @param converter the converter convert the string to other types, usually enum.
     * @return its value.
     */
    @SuppressWarnings("unchecked")
    default <T1> T1 get(Supplier<By> selector, Locator<String, T1> converter) {
        return converter.locate(new Input<>((T) this, selector).getValue());
    }

    /**
     * Read value from an input field.
     *
     * @param selector selector
     * @return its value.
     */
    @SuppressWarnings("unchecked")
    default String get(Supplier<By> selector) {
        return new Input<>((T) this, selector).getValue();
    }

    /**
     * Enter text into an input field.
     *
     * @param selector selector
     * @param value    value
     */
    @SuppressWarnings("unchecked")
    default void put(Supplier<By> selector, Object value) {
        new Input<>((T) this, selector).put(value);
    }

    @SuppressWarnings("unchecked")
    default void upload(Supplier<By> selector, Supplier<By> submit, File filePath) {
        new FileInput<>((T) this, selector).put(filePath);
        button(submit).click();
    }

    /**
     * Autocomplete for text field and return the first found suggestion match the whole word.
     *
     * @param selector selector
     * @param value    value
     * @param locator  locator
     */
    @SuppressWarnings("unchecked")
    default void autocomplete(Supplier<By> selector, Object value, Locator<T, Stream<Element>> locator) {
        new Input<>((T) this, selector).autocomplete(value, locator);
    }


}
