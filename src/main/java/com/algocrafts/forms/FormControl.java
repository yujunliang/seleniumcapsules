package com.algocrafts.forms;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.locators.SelectLocator;
import org.openqa.selenium.By;

import java.util.function.Predicate;
import java.util.function.Supplier;


public interface FormControl<Where extends Searchable<Where>> {

    /**
     * Set checkbox to the given value.
     *
     * @param method
     * @param value
     */
    default public void check(Supplier<By> method, boolean value) {
        new Checkbox<>((Where) this, method).setValue(value);
    }

    /**
     * Choose the radio by given option.
     *
     * @param method
     * @param option
     */
    default public void radio(Supplier<By> method, Object option) {
        new RadioButton<>((Where) this, method).setValue(option);
    }

    /**
     * Select the dropdown by given value.
     *
     * @param method
     * @param value
     */
    default public void select(Supplier<By> method, Object value) {
        new Select<>((Where) this, new SelectLocator<Where>(new ElementLocator<>(method))).selectByVisibleText(value);
    }

    /**
     * Enter text into an input filed.
     * @param method
     * @param value
     */
    default public void put(Supplier<By> method, Object value ) {
          new Input<>((Where)this).put(method, value);
    }

    default public void autocomplete(Supplier<By> method, Object value, Locator<Where, Element> locator) {
        new Input<>((Where) this).autocomplete(method, value, locator);
    }

}
