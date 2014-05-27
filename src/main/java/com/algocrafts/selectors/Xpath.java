package com.algocrafts.selectors;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.xpath;

/**
 * This enum has elements with ByXpath from Selenium By API.
 */
public enum Xpath implements Supplier<By> {

    DIV_CONTAINER_ID("//div[@id='container']"),
    ORACLE_AUTOCOMPLETE("//table[contains(@class, 'gssb_c')]/descendant::span[text()='oracle']"),
    QUANTITY("//div[@id='ys_cartInfo']/descendant::input[@name='cartDS.shoppingcart_ROW0_m_orderItemVector_ROW0_m_quantity']");

    private final By by;

    private Xpath(String id) {
        this.by = xpath(id);
    }

    /**
     * @return the by instance variable which is a ByXpath.
     */
    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}
