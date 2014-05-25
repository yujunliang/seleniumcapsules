package com.algocrafts.searchmethods;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.xpath;

public enum Xpath implements Supplier<By> {

    DIV_CONTAINER_ID("//div[@id='container']"),
    QUANTITY("//div[@id='ys_cartInfo']/descendant::input[@name='cartDS.shoppingcart_ROW0_m_orderItemVector_ROW0_m_quantity']");

    private final By by;

    private Xpath(String id) {
        this.by = xpath(id);
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
