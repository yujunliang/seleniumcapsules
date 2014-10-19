package com.algocrafts.chapter5;


import com.algocrafts.locators.Locators;
import com.algocrafts.selectors.TagName;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Element;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.OptionalPresents.PRESENT;
import static com.algocrafts.selectors.ClassName.L_TINYNAL1;
import static com.algocrafts.selectors.Id.GLOBAL_NAV;
import static com.algocrafts.selectors.TagName.LI;
import static com.algocrafts.selectors.TagName.UL;
import static java.util.stream.Collectors.toList;


public class NaiveJQueryHomePageTest {

    @Test
    public void mouseOver4() {
        Browser<ChromeDriver> browser = CHROME;
        browser.get("https://jquery.org");
        List<Element> elements = browser.untilFound(GLOBAL_NAV)
                .untilFound(L_TINYNAL1)
                .findElements(LI)
                .filter(DISPLAYED.and(Locators.<Element>optionalElement(UL).and(PRESENT.negate()).or(
                        Locators.<Element>optionalElement(LI).and(PRESENT)))).collect(toList());
        List<Element> menu = new ArrayList<>();
        elements.forEach(menubarElement -> {
                    Element link = menubarElement.untilFound(TagName.A);
                    System.out.println("Menu Bar[" + link.getText() + "]");
                    menu.add(link);
                    browser.mouseOver(menubarElement);
                    Optional<Element> optionalElement = menubarElement.optionalElement(UL);
                    if (optionalElement.isPresent()) {
                        Stream<Element> meneElements = optionalElement.get()
                                .findElements(LI)
                                .map(liElement -> {
                                    Element menuElement = liElement.untilFound(TagName.A);
                                    System.out.println("  |-- Menu[" + menuElement.getText() + "]");
                                    return menuElement;
                                });
                        menu.addAll(meneElements.collect(toList()));
                    }
                }
        );

        menu.forEach(Element::click);
    }
}
