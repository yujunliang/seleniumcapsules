package com.algocrafts.chapter4;


import com.algocrafts.locators.Locators;
import com.algocrafts.selectors.TagName;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Element;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
    public void noFiltering() {
        Browser<ChromeDriver> browser = CHROME;
        browser.get("https://jquery.org");
        Stream<Element> elements = browser.untilFound(() -> By.id("global-nav"))
                .untilFound(() -> By.className("l_tinynav1"))
                .findElements(() -> By.tagName("li"))
                .map(e1 -> e1.untilFound(() -> By.tagName("a")));
        elements.forEach(System.out::println);
    }

    @Test
    public void filtering() {
        Browser<ChromeDriver> browser = CHROME;
        browser.get("https://jquery.org");
        Stream<Element> elements = browser.untilFound(() -> By.id("global-nav"))
                .untilFound(() -> By.className("l_tinynav1"))
                .findElements(() -> By.tagName("li"))
                .filter((Element e) ->
                        e.isDisplayed() &&
                                (!e.optionalElement(() -> By.tagName("ul")).isPresent()) ||
                                e.optionalElement(() -> By.tagName("li")).isPresent());
        elements.forEach(System.out::println);
    }

    @Test
    public void mouseOver() {
        Browser<ChromeDriver> browser = CHROME;
        browser.get("https://jquery.org");
        Stream<Element> elements = browser.untilFound(() -> By.id("global-nav"))
                .untilFound(() -> By.className("l_tinynav1"))
                .findElements(() -> By.tagName("li"))
                .filter((Element e) ->
                        e.isDisplayed() &&
                                (!e.optionalElement(() -> By.tagName("ul")).isPresent()) ||
                                e.optionalElement(() -> By.tagName("li")).isPresent());
        List<Element> menu = new ArrayList<>();
        elements.forEach(e -> {
                    Element a = e.untilFound(() -> By.tagName("a"));
                    menu.add(a);
                    new Actions(browser).moveToElement(e).perform();
                    Optional<Element> optionalElement =
                            e.optionalElement(() -> By.tagName("ul"));
                    if (optionalElement.isPresent()) {
                        Stream<Element> elements2 = optionalElement.get()
                                .findElements(() -> By.tagName("li"))
                                .map(e1 -> e1.untilFound(() -> By.tagName("a")));
                        menu.addAll(elements2.collect(toList()));
                    }
                }
        );
        menu.stream().forEach(a -> System.out.println("Menu[" + a.getText() + "]"));
    }

    @Test
    public void mouseOver2() {
        Browser<ChromeDriver> browser = CHROME;
        browser.get("https://jquery.org");
        Stream<Element> elements = browser.untilFound(() -> By.id("global-nav"))
                .untilFound(() -> By.className("l_tinynav1"))
                .findElements(() -> By.tagName("li"))
                .filter((Element e) ->
                        e.isDisplayed() &&
                                (!e.optionalElement(() -> By.tagName("ul")).isPresent()) ||
                                e.optionalElement(() -> By.tagName("li")).isPresent());
        List<Element> menu = new ArrayList<>();
        elements.forEach(e -> {
                    Element a = e.untilFound(() -> By.tagName("a"));
                    System.out.println("Menu Bar[" + a.getText() + "]");
                    menu.add(a);
                    new Actions(browser).moveToElement(e).perform();
                    Optional<Element> optionalElement = e.optionalElement(() -> By.tagName("ul"));
                    if (optionalElement.isPresent()) {
                        Stream<Element> elements2 = optionalElement.get()
                                .findElements(() -> By.tagName("li"))
                                .map(e3 -> {
                                    Element a1 = e3.untilFound(() -> By.tagName("a"));
                                    System.out.println("  |-- Menu[" + a1.getText() + "]");
                                    return a1;
                                });
                        menu.addAll(elements2.collect(toList()));
                    }
                }
        );
    }


    @Test
    public void mouseOver3() {
        Browser<ChromeDriver> browser = CHROME;
        browser.get("https://jquery.org");
        List<Element> elements = browser.untilFound(() -> By.id("global-nav"))
                .untilFound(() -> By.className("l_tinynav1"))
                .findElements(() -> By.tagName("li"))
                .filter((Element e) ->
                        e.isDisplayed() &&
                                (!e.optionalElement(() -> By.tagName("ul")).isPresent()) ||
                                e.optionalElement(() -> By.tagName("li")).isPresent()).collect(toList());
        List<Element> menu = new ArrayList<>();
        elements.forEach(e -> {
                    Element a = e.untilFound(() -> By.tagName("a"));
                    System.out.println("Menu Bar[" + a.getText() + "]");
                    menu.add(a);
                    new Actions(browser).moveToElement(e).perform();
                    Optional<Element> optionalElement = e.optionalElement(() -> By.tagName("ul"));
                    if (optionalElement.isPresent()) {
                        Stream<Element> elements2 = optionalElement.get()
                                .findElements(() -> By.tagName("li"))
                                .map(e3 -> {
                                    Element a1 = e3.untilFound(() -> By.tagName("a"));
                                    System.out.println("  |-- Menu[" + a1.getText() + "]");
                                    return a1;
                                });
                        menu.addAll(elements2.collect(toList()));
                    }
                }
        );
    }

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
        elements.forEach(e -> {
                    Element a = e.untilFound(TagName.A);
                    System.out.println("Menu Bar[" + a.getText() + "]");
                    menu.add(a);
                    browser.mouseOver(e);
                    Optional<Element> optionalElement = e.optionalElement(UL);
                    if (optionalElement.isPresent()) {
                        Stream<Element> elements2 = optionalElement.get()
                                .findElements(LI)
                                .map(e3 -> {
                                    Element a1 = e3.untilFound(TagName.A);
                                    System.out.println("  |-- Menu[" + a1.getText() + "]");
                                    return a1;
                                });
                        menu.addAll(elements2.collect(toList()));
                    }
                }
        );
    }
}
