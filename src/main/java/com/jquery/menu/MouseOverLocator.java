package com.jquery.menu;


import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.ClassName;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_NULL;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.Id.GLOBAL_NAV;
import static com.algocrafts.selectors.TagName.LI;
import static com.algocrafts.selectors.TagName.UL;
import static org.openqa.selenium.By.linkText;

public class MouseOverLocator implements Locator<Page, Element> {

    private final String menuGroup;
    private final String menuItem;

    public MouseOverLocator(String menuGroup, String menuItem) {
        this.menuGroup = menuGroup;
        this.menuItem = menuItem;
    }

    public Element locate(Page page) {
        return new ElementLocator<Page>(GLOBAL_NAV)
                .and(new ElementLocator<>(ClassName.L_TINYNAL1))
                        .and(elements(LI))
                        .and(new FirstMatch<>(NOT_NULL.and(DISPLAYED).and(TEXT.and(new Equals(menuGroup)))))
                        .and(GET)
                        .and(page.mouseOver())
                        .and(new ElementLocator<>(UL))
                        .and(new ElementLocator<>((Supplier<By>) () -> linkText(menuItem)))
                        .locate(page);
    }

    @Override
    public String toString() {
        return "[" + menuGroup + "->" + menuItem + "]";
    }
}
