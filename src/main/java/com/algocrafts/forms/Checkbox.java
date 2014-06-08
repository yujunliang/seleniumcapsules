package com.algocrafts.forms;


import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locating;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static com.algocrafts.conditions.StringEquals.TRUE;
import static com.algocrafts.converters.GetText.CHECKED;
import static com.algocrafts.pages.Locators.element;

public class Checkbox<Where extends Searchable<Where>> extends Locating<Where, Element> {

    /**
     * Constructor of the checkbox.
     *
     * @param where    the place the checkbox can be found
     * @param selector the selector that leads to the checkbox
     */
    public Checkbox(final Where where, Supplier<By> selector) {
        super(where, element(selector));
    }

    /**
     * Change the checkbox according to the value parameter
     *
     * @param value true or false
     */
    public void setValue(boolean value) {
        Element checkbox = get();
        if (checkbox != null && checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    /**
     * @return whether the checkbox is checked or not
     */
    public boolean isChecked() {
        return CHECKED.and(TRUE).test(get());
    }
}
