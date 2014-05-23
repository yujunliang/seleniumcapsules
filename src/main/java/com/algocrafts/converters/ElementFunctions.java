package com.algocrafts.converters;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;

public enum ElementFunctions implements Locator<Element, Void> {

    CLICK_IF_NOT_NULL {
        @Override
        public Void apply(Element input) {
            if (input != null) {
                input.click();
            }
            return null;
        }
    }

}