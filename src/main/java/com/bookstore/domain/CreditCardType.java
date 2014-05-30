package com.bookstore.domain;

import static com.algocrafts.converters.EnumToString.REPLACE_UNDERSCORE;

public enum CreditCardType {

    American_Express,
    JCB,
    MasterCard,
    Visa,
    Discover;

    @Override
    public String toString() {
        return REPLACE_UNDERSCORE.locate(this);
    }
}
