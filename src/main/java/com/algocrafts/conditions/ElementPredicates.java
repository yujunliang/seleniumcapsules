package com.algocrafts.conditions;

import com.algocrafts.pages.Element;

import java.util.function.Predicate;

public enum ElementPredicates implements Predicate<Element> {

    DISPLAYED {
        @Override
        public boolean test(Element input) {
            return input != null && input.isDisplayed();
        }
    },
    NOT_DISPLAYED {
        @Override
        public boolean test(Element input) {
            return input == null || !input.isDisplayed();
        }
    },
    ENABLED {
        @Override
        public boolean test(Element input) {
            return input != null && input.isEnabled();
        }
    },
    NOT_ENABLED {
        @Override
        public boolean test(Element input) {
            return input != null && !input.isEnabled();
        }
    },
    SELECTED {
        @Override
        public boolean test(Element input) {
            return input != null && input.isSelected();
        }
    },
    NOT_SELECTED {
        @Override
        public boolean test(Element input) {
            return input != null && !input.isSelected();
        }
    },
}