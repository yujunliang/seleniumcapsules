package com.algocrafts.searchmethods;

import org.openqa.selenium.By;

import java.util.function.Supplier;

public enum Name implements Supplier<By> {

    Q("q"),
    MAILING_OPTION("customFieldDS.customfield_ROW0_value"),
    QUANTITY("cartDS.shoppingcart_ROW0_m_orderItemVector_ROW0_m_quantity");

    private final By by;

    private Name(String id) {
        this.by = By.name(id);
    }

    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}