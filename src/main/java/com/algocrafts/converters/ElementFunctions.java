package com.algocrafts.converters;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

public enum ElementFunctions implements Locator<Element, Void> {

    CLICK {
        @Override
        public Void locate(Element input) {
            input.click();
            return null;
        }
    }
}