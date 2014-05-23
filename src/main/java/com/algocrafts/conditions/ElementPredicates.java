package com.algocrafts.conditions;

import com.algocrafts.pages.Element;

import java.util.function.Predicate;

public enum ElementPredicates implements Predicate<Element> {

    DISPLAYED {
        @Override
        public boolean test(Element input) {
            return input.isDisplayed();
        }
    },
    NOT_DISPLAYED {
        @Override
        public boolean test(Element input) {
            return !input.isDisplayed();
        }
    },
    ENABLED {
        @Override
        public boolean test(Element input) {
            return input.isEnabled();
        }
    },
    NOT_ENABLED {
        @Override
        public boolean test(Element input) {
            return !input.isEnabled();
        }
    },
    SELECTED {
        @Override
        public boolean test(Element input) {
            return input.isSelected();
        }
    },
    NOT_SELECTED {
        @Override
        public boolean test(Element input) {
            return !input.isSelected();
        }
    },
}