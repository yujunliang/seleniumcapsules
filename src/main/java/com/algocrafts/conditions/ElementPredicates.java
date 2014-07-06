package com.algocrafts.conditions;

import com.algocrafts.selenium.Element;

import java.util.function.Predicate;

public enum ElementPredicates implements Predicate<Element> {
    NOT_NULL {
        @Override
        public boolean test(Element input) {
            return input != null;
        }
    },
    DISPLAYED {
        @Override
        public boolean test(Element input) {
            return input.isDisplayed();
        }
    },
    ENABLED {
        @Override
        public boolean test(Element input) {
            return input.isEnabled();
        }
    },
    SELECTED {
        @Override
        public boolean test(Element input) {
            return input.isSelected();
        }
    },
}