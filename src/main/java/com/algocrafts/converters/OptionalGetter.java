package com.algocrafts.converters;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import java.util.Optional;

public enum OptionalGetter implements Locator<Optional<Element>, Element>{
    GET;

    @Override
    public Element locate(Optional<Element> elementOptional) {
        return elementOptional.get();
    }
}
