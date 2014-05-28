package com.algocrafts.converters;


import com.algocrafts.selenium.Locator;

public enum EnumToString implements Locator<Enum, String> {

    REPLACE_UNDERSCORE {
        @Override
        public String locate(Enum element) {
            return element.name().replace('_', ' ');
        }
    };
}
