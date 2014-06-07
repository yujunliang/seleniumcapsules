package com.algocrafts.domain;

import static com.algocrafts.converters.StringConverter.REPLACE_UNDERSCORE;
import static com.algocrafts.converters.StringConverter.RESTORE_UNDERSCORE;

public enum CreditCardType {

    American_Express,
    JCB,
    MasterCard,
    Visa,
    Discover;

    @Override
    public String toString() {
        return REPLACE_UNDERSCORE.locate(this.name());
    }

    public static CreditCardType fromString(String string) {
        return valueOf(RESTORE_UNDERSCORE.locate(string));
    }
}
