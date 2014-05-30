package com.bookstore.domain;


import static com.algocrafts.converters.EnumToString.REPLACE_UNDERSCORE;

public enum Countries {
    Australia, China, India, United_Kindon, United_States;

    @Override
    public String toString() {
        return REPLACE_UNDERSCORE.locate(this);
    }
}
