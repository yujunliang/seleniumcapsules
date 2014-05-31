package com.bookstore;

import com.algocrafts.pages.AbstractPage;
import com.bookstore.domain.Address;
import com.algocrafts.domain.Countries;
import com.algocrafts.domain.UnitedStates;

import static com.bookstore.BookStoreId.*;

public class BillingAddressForm extends AbstractPage {
    public BillingAddressForm(AbstractPage page) {
        super(page);
    }

    public void setBillingAddress(Address address) {
        put(BILLING_FIRST_NAME, address.firstName);
        put(BILLING_LAST_NAME_, address.lastName);
        put(BILLING_ADDRESS1__, address.street1);
        put(BILLING_ADDRESS2__, address.street2);
        put(BILLING_CITY______, address.city);
        put(BILLING_STATE_____, address.state);
        put(BILLING_ZIP_______, address.zipcode);
        select(BILLING_COUNTRY___, address.country);
    }

    public Address getBillingAddress() {
        return new Address(
                get(BILLING_ADDRESS1__),
                get(BILLING_ADDRESS2__),
                get(BILLING_CITY______),
                get(BILLING_ZIP_______),
                UnitedStates.fromString(get(BILLING_STATE_____)),
                Countries.fromString(get(BILLING_COUNTRY___)),
                get(BILLING_FIRST_NAME),
                get(BILLING_LAST_NAME_));
    }

}
