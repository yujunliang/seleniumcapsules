package com.algocrafts.locators;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class ElementLocator<Where extends Searchable<Where>> extends AbstractLocator<Where, Element> {

    public ElementLocator(Supplier<By> method) {
        super(method);
    }

    @Override
    public Element find(Where where) {
        return where.untilFound(by);
    }
}