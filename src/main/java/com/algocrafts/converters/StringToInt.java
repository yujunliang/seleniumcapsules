package com.algocrafts.converters;


import com.algocrafts.pages.Locator;

import static java.lang.Integer.parseInt;

public enum StringToInt implements Locator<String, Integer> {

    PARSE_INT {
        @Override
        public Integer locate(String element) {
            return parseInt(element);
        }

    }
}
