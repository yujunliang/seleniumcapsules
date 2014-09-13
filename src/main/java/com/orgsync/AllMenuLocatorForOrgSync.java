package com.orgsync;


import com.algocrafts.clickables.Menu;
import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.Filter;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.pages.*;
import com.algocrafts.locators.Locators;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_NULL;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.*;
import static com.algocrafts.selectors.ClassName.SF_JS_ENABLED;
import static com.algocrafts.selectors.Id.MAIN_NAV;
import static com.algocrafts.selectors.TagName.*;
import static com.google.common.collect.Lists.newArrayList;

public class AllMenuLocatorForOrgSync implements Locator<Page, Stream<Clickable>> {

    @Override
    public Stream<Clickable> locate(Page page) {

        Locator<Page, Stream<Element>> MENU_BAR =
                Locators.<Page>element(MAIN_NAV)
                        .andThen(element(SF_JS_ENABLED))
                        .andThen(elements(LI))
                        .andThen(new Filter<>(NOT_NULL.and(DISPLAYED)));

        Locator<Element, String> LINK_TEXT = Locators.<Element>element(A).andThen(TEXT);
        Locator<Element, Optional<Element>> MENU_GROUP = Locators.<Element>optionalElement(UL);

        List<Clickable> allMenu = newArrayList();
        MENU_BAR.locate(page).forEach(header -> {
            try {
                Element menubar = MENU_BAR
                        .andThen(new FirstMatch<>(TEXT.and(new Equals(LINK_TEXT.locate(header)))))
                        .andThen(GET)
                        .locate(page);

                String group = LINK_TEXT.locate(menubar);
                allMenu.add(new Menu(page, new MenuGroupLocator(group)));

                page.mouseOver(header);
                Optional<Element> menuGroup = MENU_GROUP.locate(menubar);
                if (menuGroup.isPresent()) {
                    menuGroup.get().until(NOT_NULL.and(DISPLAYED));
                    Locators.<Element>elements(LI).locate(menubar).forEach(menu ->
                                    allMenu.add(new Menu(page, new MouseOverLocator(group, page.mouseOver().andThen(LINK_TEXT).locate(menu))))
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return allMenu.stream();
    }
}
