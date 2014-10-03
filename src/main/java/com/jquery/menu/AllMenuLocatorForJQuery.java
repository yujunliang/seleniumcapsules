package com.jquery.menu;


import com.algocrafts.clickables.Menu;
import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.Filter;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.ClassName;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.OptionalPresents.PRESENT;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.Id.GLOBAL_NAV;
import static com.algocrafts.selectors.TagName.*;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

public class AllMenuLocatorForJQuery implements Locator<Page, Stream<Clickable>> {

    private static final Locator<Page, Stream<Element>> MENU_BAR =
            new ElementLocator<Page>(GLOBAL_NAV)
                    .and(new ElementLocator<>(ClassName.L_TINYNAL1))
                    .and(elements(LI))
                    .and(new Filter<>(DISPLAYED
                            .and(Locators.<Element>optionalElement(UL).and(PRESENT.negate())
                                    .or(Locators.<Element>optionalElement(LI).and(PRESENT)))));

    private static final Locator<Element, String> LINK_TEXT =
            new ElementLocator<Element>(A).and(TEXT);
    private static final Locator<Element, Optional<Element>> MENU_GROUP =
            Locators.<Element>optionalElement(UL);

    @Override
    public Stream<Clickable> locate(Page page) {

        List<Clickable> allMenu = newArrayList();
        MENU_BAR.locate(page).forEach(header -> {

            Element menubar = MENU_BAR
                    .and(new FirstMatch<>(TEXT.and(new Equals(LINK_TEXT.locate(header)))))
                    .and(GET)
                    .locate(page);

            String group = LINK_TEXT.locate(menubar);
            System.out.println("group" + group);
            allMenu.add(new Menu(page, new MenuGroupLocator(group)));

            page.mouseOver(header);
            Optional<Element> menuGroup = MENU_GROUP.locate(menubar);
            if (menuGroup.isPresent()) {
                menuGroup.get().until(DISPLAYED);
                allMenu.addAll(Locators.<Element>elements(LI).locate(menubar).map(
                        menu -> new Menu(page, new MouseOverLocator(group, page.mouseOver().and(LINK_TEXT).locate(menu)))).collect(toList()));

            }

        });

        return allMenu.stream();
    }
}
