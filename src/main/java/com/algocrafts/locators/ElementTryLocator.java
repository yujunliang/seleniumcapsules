package com.algocrafts.locators;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class ElementTryLocator<Where extends Searchable<Where>> extends AbstractLocator<Where, Element> {

    public ElementTryLocator(Supplier<By> method) {
        super(method);
    }

    @Override
    public Element find(Where where) {
        return where.findElement(by);
    }
}