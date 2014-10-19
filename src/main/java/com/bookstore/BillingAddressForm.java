package com.bookstore;

import com.algocrafts.domain.Countries;
import com.algocrafts.domain.UnitedStates;
import com.algocrafts.pages.Page;
import com.bookstore.domain.Address;

import static com.bookstore.BookStoreId.*;

public class BillingAddressForm  {
    private Page page;

    public BillingAddressForm(Page page) {
        this.page = page;
    }

    public void setBillingAddress(Address address) {
        page.put(BILLING_FIRST_NAME, address.firstName);
        page.put(BILLING_LAST_NAME, address.lastName);
        page.put(BILLING_ADDRESS1, address.street1);
        page.put(BILLING_ADDRESS2, address.street2);
        page.put(BILLING_CITY, address.city);
        page.put(BILLING_STATE, address.state);
        page.put(BILLING_ZIP, address.zipcode);
        page.select(BILLING_COUNTRY, address.country);
    }

    public Address getBillingAddress() {
        return new Address(
                page.get(BILLING_ADDRESS1),
                page.get(BILLING_ADDRESS2),
                page.get(BILLING_CITY),
                page.get(BILLING_ZIP),
                page.get(BILLING_STATE, UnitedStates::fromString),
                page.get(BILLING_COUNTRY, Countries::fromString),
                page.get(BILLING_FIRST_NAME),
                page.get(BILLING_LAST_NAME));
    }

}
