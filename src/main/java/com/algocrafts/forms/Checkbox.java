package com.algocrafts.forms;


import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locating;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static com.algocrafts.conditions.StringEquals.TRUE;
import static com.algocrafts.converters.GetText.CHECKED;
import static com.algocrafts.locators.Locators.element;

public class Checkbox<Where extends SearchScope<Where>> extends Locating<Where, Element> {

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
        locate((Element checkbox) -> {
            if (checkbox != null && checkbox.isSelected() != value) {
                checkbox.click();
            }
            return null;
        });
    }

    /**
     * @return whether the checkbox is checked or not
     */
    public boolean isChecked() {
        return test(CHECKED.and(TRUE));
    }
}
