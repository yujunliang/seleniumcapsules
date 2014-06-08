package com.algocrafts.converters;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

public enum ElementFunctions implements Locator<Element, Void> {

    CLICK {
        @Override
        public Void locate(Element input) {
            if (input != null) {
                input.click();
            }
            return null;
        }
    }

}