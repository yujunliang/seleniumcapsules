package com.algocrafts.domain;


import static com.algocrafts.converters.StringConverter.REPLACE_UNDERSCORE;
import static com.algocrafts.converters.StringConverter.RESTORE_UNDERSCORE;

public enum Countries {
    Australia,
    China,
    India,
    United_Kindon,
    United_States;

    @Override
    public String toString() {
        return REPLACE_UNDERSCORE.locate(this.name());
    }

    public static Countries fromString(String string) {
        return valueOf(RESTORE_UNDERSCORE.locate(string));
    }
}
