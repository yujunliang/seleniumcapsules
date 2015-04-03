package com.algocrafts.converters;

import com.algocrafts.locators.ElementLocator;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.selectors.TagName.A;

public enum GetText implements Locator<Element, String> {
    CHECKED {
        @Override
        public String locate(Element input) {
            return input.getAttribute("checked");
        }
    },
    VALUE {
        @Override
        public String locate(Element input) {
            return input.getAttribute("value");
        }
    },
    SRC {
        @Override
        public String locate(Element input) {
            return input.getAttribute("src");
        }
    },
    TEXT {
        @Override
        public String locate(Element input) {
            return input.getText();
        }
    },
    LINK_TEXT {
        @Override
        public String locate(Element input) {
            return new ElementLocator<Element>(A).andThen(TEXT).locate(input);
        }
    }
}
