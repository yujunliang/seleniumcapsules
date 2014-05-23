package com.algocrafts.converters;


import com.algocrafts.pages.Locator;

public enum EnumToString implements Locator<Enum, String> {

    REPLACE_UNDERSCORE {
        @Override
        public String apply(Enum element) {
            return element.name().replace('_', ' ');
        }
    };
}
