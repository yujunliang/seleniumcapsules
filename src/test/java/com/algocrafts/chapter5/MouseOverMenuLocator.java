package com.algocrafts.chapter5;

import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.ClassName;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.Id.GLOBAL_NAV;
import static com.algocrafts.selectors.TagName.LI;
import static com.algocrafts.selectors.TagName.UL;
import static org.openqa.selenium.By.linkText;

public class MouseOverMenuLocator implements Locator<Page, Element> {

    private final String menuGroup;
    private final String menuText;

    public MouseOverMenuLocator(String menuGroup, String menuText) {
        this.menuGroup = menuGroup;
        this.menuText = menuText;
    }

    public Element locate(Page page) {
        return Locators.<Page>element(GLOBAL_NAV)
                .and(element(ClassName.L_TINYNAL1))
                .and(elements(LI))
                .and(new FirstMatch<>(DISPLAYED
                        .and(TEXT.and(new Equals(menuGroup)))))
                .and(GET)
                .and(page.mouseOver())
                .and(element(UL))
                .and(element(() -> linkText(menuText)))
                .locate(page);
    }

    @Override
    public String toString() {
        return "[" + menuGroup + "->" + menuText + "]";
    }
}