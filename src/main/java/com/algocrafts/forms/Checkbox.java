package com.algocrafts.forms;


import com.algocrafts.pages.Clickable;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import com.algocrafts.locators.ElementLocator;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static com.algocrafts.converters.ElementFunctions.CLICK_IF_NOT_NULL;
import static com.algocrafts.converters.GetText.CHECKED;
import static com.algocrafts.conditions.StringEquals.TRUE;

class Checkbox<Where extends Searchable<Where>> implements Clickable {

    private final Where where;
    private final ElementLocator<Where> locator;

    Checkbox(final Where where, Supplier<By> locator) {
        this.where = where;
        this.locator = new ElementLocator<>(locator);
    }

    public void setValue(boolean value) {
        if (value) {
            check();
        } else {
            uncheck();
        }
    }

    public void check() {
        Element apply = locator.locate(where);
        if (apply != null) {
            if (!apply.isSelected()) {
                apply.click();
            }
        }
    }

    public void uncheck() {
        Element apply = locator.locate(where);
        if (apply != null) {
            if (apply.isSelected()) {
                apply.click();
            }
        }
    }

    public boolean isChecked() {
        return locator.and(CHECKED).and(TRUE).test(where);
    }

    public boolean isNotChecked() {
        return !isChecked();
    }

    /**
     * This method toggle the value of the checkbox
     */
    @Override
    public void click() {
        locator.and(CLICK_IF_NOT_NULL).locate(where);
    }
}
