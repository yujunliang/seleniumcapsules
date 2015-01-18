package com.daugherty;


import com.algocrafts.clickables.Menu;
import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.Filter;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.Id;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.By;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.TagName.A;
import static com.algocrafts.selectors.TagName.LI;
import static com.google.common.collect.Lists.newArrayList;

public class AllMenuLocatorForDaugherty implements Locator<Page, Stream<Clickable>> {

    @Override
    public Stream<Clickable> locate(Page page) {

        List<Clickable> allMenu = getClickables(page, Id.MAIN_NAV);

        allMenu.addAll(getClickables(page, Id.SECONDARY_NAV));
        return allMenu.stream();
    }

    private List<Clickable> getClickables(Page page, Supplier<By> id) {
        Locator<Page, Stream<Element>> menubars =
            new ElementLocator<Page>(id)
                    .andthen(elements(LI))
                .andthen(new Filter<>(DISPLAYED));

        Locator<Element, String> LINK_TEXT = new ElementLocator<Element>(A).andthen(TEXT);

        List<Clickable> allMenu = newArrayList();
        menubars.apply(page).forEach(header -> {
            try {
                Element menubar = menubars
                        .andthen(new FirstMatch<>(TEXT.and(new Equals(LINK_TEXT.locate(header)))))
                        .andthen(GET)
                        .locate(page);

                String group = LINK_TEXT.apply(menubar);
                allMenu.add(new Menu(page, new MenuGroupLocator(group, id)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return allMenu;
    }
}
