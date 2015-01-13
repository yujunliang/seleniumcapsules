package com.jquery.menu;


import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.ClassName;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_NULL;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
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
        return new ElementLocator<Page>(GLOBAL_NAV)
                .andNext(new ElementLocator<>(ClassName.L_TINYNAL1))
                .andNext(elements(LI))
                .andNext(new FirstMatch<>(NOT_NULL.and(DISPLAYED).and(TEXT.and(new Equals(menuGroup)))))
                .andNext(GET)
                .andNext(page.mouseOver())
                .andNext(new ElementLocator<>(UL))
                .andNext(new ElementLocator<>(() -> linkText(menuText)))
                .locate(page);
    }

    @Override
    public String toString() {
        return "[" + menuGroup + "->" + menuText + "]";
    }
}
