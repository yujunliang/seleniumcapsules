package com.extjs;


import com.algocrafts.converters.FrameLocator;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.LinkText;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.selectors.LinkText.EXTJS_DATEPICKER_TRIGGER;

public enum TriggerClicker implements Locator<Page, Void> {
    TRIGGER(
            new FrameLocator(1).andThen(Locators.<Page>element(EXTJS_DATEPICKER_TRIGGER))
    );

    private final Locator<Page, Element> locator;

    private TriggerClicker(Locator<Page, Element> locator) {
        this.locator = locator;
    }

    @Override
    public Void locate(Page page) {
        locator.andThen(page.mouseOver()).locate(page);
        return null;
    }
}
