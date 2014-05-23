package com.algocrafts.converters;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;

public enum GetText implements Locator<Element, String> {
    CHECKED {
        @Override
        public String apply(Element input) {
            return input.getAttribute("checked");
        }
    },
    VALUE {
        @Override
        public String apply(Element input) {
            return input.getAttribute("value");
        }
    },
    SRC {
        @Override
        public String apply(Element input) {
            return input.getAttribute("src");
        }
    },
    TEXT {
        @Override
        public String apply(Element input) {
            return input.getText();
        }
    };
}
