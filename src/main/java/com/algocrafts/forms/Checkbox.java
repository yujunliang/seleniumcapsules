package com.algocrafts.forms;


import com.algocrafts.pages.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static com.algocrafts.conditions.StringEquals.TRUE;
import static com.algocrafts.converters.GetText.CHECKED;
import static com.algocrafts.pages.Locators.element;

public class Checkbox<Where extends Searchable<Where>> {

    private final Where where;
    private final Locator<Where, Element> locator;

    /**
     * Constructor of the checkbox.
     *
     * @param where    the place the checkbox can be found
     * @param selector the selector that leads to the checkbox
     */
    Checkbox(final Where where, Supplier<By> selector) {
        this.where = where;
        this.locator = element(selector);
    }

    /**
     * Change the checkbox according to the value parameter
     *
     * @param value true or false
     */
    public void setValue(boolean value) {
        Element checkbox = locator.locate(where);
        if (checkbox != null && checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    /**
     * @return whether the checkbox is checked or not
     */
    public boolean isChecked() {
        return locator.and(CHECKED).and(TRUE).test(where);
    }
}
