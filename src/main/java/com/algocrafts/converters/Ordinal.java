package com.algocrafts.converters;


import com.algocrafts.pages.Locator;

public enum Ordinal implements Locator<Enum, Integer> {

    ORDINAL {
        @Override
        public Integer locate(Enum input) {
            return input.ordinal();
        }
    };
}
