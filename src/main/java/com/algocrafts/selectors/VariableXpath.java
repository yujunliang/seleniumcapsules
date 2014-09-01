package com.algocrafts.selectors;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.xpath;

/**
 * This enum is a Supplier ByXpath from Selenium By API.
 */
public enum VariableXpath implements SupplierConverter {

    GOOGLE_AUTOCOMPLETE("//table[contains(@class, 'gssb_c')]/descendant::span[text()='$value']");

    private final String by;

    private VariableXpath(String id) {
        this.by = id;
    }

    public Supplier<By> of(Object part) {
        return () -> xpath(by.replaceAll("$value", part.toString()));
    }
}
