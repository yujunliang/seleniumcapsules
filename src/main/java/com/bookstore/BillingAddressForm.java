package com.bookstore;

import com.algocrafts.domain.Countries;
import com.algocrafts.domain.UnitedStates;
import com.algocrafts.pages.AbstractPage;
import com.bookstore.domain.Address;

import static com.bookstore.BookStoreId.*;

public class BillingAddressForm extends AbstractPage {
    public BillingAddressForm(AbstractPage page) {
        super(page);
    }

    public void setBillingAddress(Address address) {
        put(BILLING_FIRST_NAME, address.firstName);
        put(BILLING_LAST_NAME_, address.lastName);
        put(BILLING_ADDRESS1, address.street1);
        put(BILLING_ADDRESS2, address.street2);
        put(BILLING_CITY, address.city);
        put(BILLING_STATE, address.state);
        put(BILLING_ZIP, address.zipcode);
        select(BILLING_COUNTRY, address.country);
    }

    public Address getBillingAddress() {
        return new Address(
                get(BILLING_ADDRESS1),
                get(BILLING_ADDRESS2),
                get(BILLING_CITY),
                get(BILLING_ZIP),
                get(BILLING_STATE, UnitedStates::fromString),
                get(BILLING_COUNTRY, Countries::fromString),
                get(BILLING_FIRST_NAME),
                get(BILLING_LAST_NAME_));
    }

}
