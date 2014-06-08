package com.bookstore;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

public enum BookStoreId implements Supplier<By> {

    BILLING_FIRST_NAME("billing-first-name"),
    BILLING_LAST_NAME_("billing-last-name"),
    BILLING_ADDRESS1__("billing-address1"),
    BILLING_ADDRESS2__("billing-address2"),
    BILLING_CITY______("billing-city"),
    BILLING_STATE_____("billing-state"),
    BILLING_COUNTRY___("billing-country"),
    BILLING_ZIP_______("billing-zip"),
    BILLING_EMAIL("billing-email"),

    CARD_TYPE("card-type"),
    CARD_NUMBER("card-number"),
    CARD_CVV("card-cvv"),
    CARD_EXP_MONTH("card-exp-month"),
    CARD_EXP_YEAR("card-exp-year"),

    COMMENTS("comments"),
    ERROR_MESSAGES("ys_errorMessages"),

    SHOPPING_CART("ys_cart"),
    YAHOO_COPYRIGHT("ys_copyright"),
    CONTENT("content"),
    FOOTER("footer");

    private final By by;

    private BookStoreId(String id) {
        this.by = id(id);
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