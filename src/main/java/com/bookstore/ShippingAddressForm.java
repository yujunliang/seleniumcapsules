package com.bookstore;

import com.algocrafts.domain.Countries;
import com.algocrafts.domain.UnitedStates;
import com.algocrafts.pages.Page;
import com.bookstore.domain.Address;

import static com.bookstore.BookStoreId.*;

public class ShippingAddressForm {
    private Page page;

    public ShippingAddressForm(Page page) {
        this.page = page;
    }

    public void setShippingAddress(Address address) {
        page.put(SHIPPING_FIRST_NAME, address.firstName);
        page.put(SHIPPING_LAST_NAME, address.lastName);
        page.put(SHIPPING_ADDRESS1, address.street1);
        page.put(SHIPPING_ADDRESS2, address.street2);
        page.put(SHIPPING_CITY, address.city);
        page.put(SHIPPING_STATE, address.state);
        page.put(SHIPPING_ZIP, address.zipcode);
        page.select(SHIPPING_COUNTRY, address.country);
    }

    public Address getShippingAddress() {
        return new Address(
                page.get(SHIPPING_ADDRESS1),
                page.get(SHIPPING_ADDRESS2),
                page.get(SHIPPING_CITY),
                page.get(SHIPPING_ZIP),
                page.get(SHIPPING_STATE, UnitedStates::fromString),
                page.get(SHIPPING_COUNTRY, Countries::fromString),
                page.get(SHIPPING_FIRST_NAME),
                page.get(SHIPPING_LAST_NAME));
    }

}
