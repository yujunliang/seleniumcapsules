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
    BILLING_EMAIL___("billing-email"),

    CARD_TYPE_________("card-type"),
    CARD_NUMBER_______("card-number"),
    CARD_CVV__________("card-cvv"),
    CARD_EXP_MONTH____("card-exp-month"),
    CARD_EXP_YEAR_____("card-exp-year"),

    RATINGS_______("ratings"),
    CONFIRM_EMAIL_("confirm-email"),
    COMMENTS________("comments"),
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