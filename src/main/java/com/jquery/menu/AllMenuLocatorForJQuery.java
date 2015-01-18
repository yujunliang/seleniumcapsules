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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.OptionalPresents.PRESENT;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.Id.GLOBAL_NAV;
import static com.algocrafts.selectors.TagName.*;
import static java.util.stream.Collectors.toList;

public class AllMenuLocatorForJQuery
        implements Locator<Page, Stream<Clickable>> {

    private static final Locator<Page, Stream<Element>> MENU_BAR =
            new ElementLocator<Page>(GLOBAL_NAV)
                    .andthen(element(ClassName.L_TINYNAL1))
                    .andthen(elements(LI))
                    .andthen(new Filter<>(DISPLAYED
                            .and(Locators.<Element>optionalElement(UL)
                                    .and(PRESENT.negate())
                                    .or(Locators.<Element>optionalElement(LI)
                                            .and(PRESENT)))));

    private static final Locator<Element, String> LINK_TEXT =
            new ElementLocator<Element>(A).andthen(TEXT);
    private static final Locator<Element, Optional<Element>> MENU_GROUP =
            Locators.<Element>optionalElement(UL);

    @Override
    public Stream<Clickable> locate(Page page) {

        List<Clickable> allMenu = new ArrayList<>();
        MENU_BAR.locate(page).forEach(header -> {

            Element menubar = MENU_BAR
                    .andthen(new FirstMatch<>(TEXT
                            .and(new Equals(LINK_TEXT.locate(header)))))
                    .andthen(GET)
                    .locate(page);

            String group = LINK_TEXT.locate(menubar);
            allMenu.add(new Menu(page, new MenuGroupLocator(group)));

            page.mouseOver(header);
            Optional<Element> menuGroup = MENU_GROUP.locate(menubar);
            if (menuGroup.isPresent()) {
                menuGroup.get().until(DISPLAYED);
                allMenu.addAll(Locators.<Element>elements(LI).locate(menubar).map(menu -> {
                    String menuText = page.mouseOver().andthen(LINK_TEXT).locate(menu);
                    return new Menu(page, new MouseOverMenuLocator(group, menuText));
                }).collect(toList()));

            }

        });

        return allMenu.stream();
    }
}
