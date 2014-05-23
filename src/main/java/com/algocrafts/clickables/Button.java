package com.algocrafts.clickables;


import com.algocrafts.algorithm.Retryable;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import com.algocrafts.pages.Locator;

public class Button<Where extends Searchable<Where>> extends AbstractClicker<Where> implements Retryable<Element> {

    public Button(Where where, Locator<Where, Element> locator) {
        super(where, locator);
    }
}
