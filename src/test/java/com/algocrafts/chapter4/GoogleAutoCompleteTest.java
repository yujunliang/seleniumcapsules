package com.algocrafts.chapter4;

import com.algocrafts.browsers.Browsers;
import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.converters.GetText;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.locators.ElementsLocator;
import com.algocrafts.selectors.ClassName;
import com.algocrafts.selectors.Name;
import com.algocrafts.selectors.TagName;
import com.algocrafts.selectors.Xpath;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;
import com.google.common.base.Function;
import com.sun.tools.corba.se.idl.constExpr.Equal;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by yujunliang on 9/28/14.
 */
public class GoogleAutoCompleteTest {

    private static final Logger log = LoggerFactory.getLogger(GoogleAutoCompleteTest.class);

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void autoCompeleteUsingSelenium() throws InterruptedException {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://google.com");
        WebElement q = webDriver.findElement(By.name("q"));

        WebElement suggestion = null;
        for (char c : "ticketfly".toCharArray()) {
            q.sendKeys(String.valueOf(c));
            try {
                suggestion = webDriver.findElement(
                        By.xpath("//div[contains(@class, " +
                                "'sbdd_b')]/descendant::div[text()='ticketfly']"));
                suggestion.click();
            } catch (NoSuchElementException e) {
                log.debug("This is OK", e);
            }
        }
        if (suggestion == null) {
            suggestion = new WebDriverWait(webDriver, 1).until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver webDriver) {
                    return webDriver.findElement(
                            By.xpath("//div[contains(@class, " +
                                    "'sbdd_b')]/descendant::div[text()='ticketfly']"));
                }
            });
            suggestion.click();
        }
    }

    @Test
    public void autoCompeleteUsingSeleniumFiltering() throws InterruptedException {
        Browser webDriver = Browsers.CHROME;
        webDriver.get("http://google.com");
        WebElement q = webDriver.findElement(By.name("q"));

        for (char c : "ticketfly".toCharArray()) {
            q.sendKeys(String.valueOf(c));

            Optional<Element> first = webDriver.untilFound(() -> By.className("sbdd_b"))
                    .untilFound(() -> By.tagName("ul"))
                    .findElements(() -> By.tagName("li"))
                    .filter((WebElement e) -> e.getText().equals("ticketfly")).findFirst();
            if (first.isPresent()) {
                first.get().click();
                break;
            }

        }
    }

    @Test
    public void autoCompeleteUsingAutocompleteMethod() throws InterruptedException {
        Browser webDriver = Browsers.CHROME;
        webDriver.get("http://google.com");
        Element q = webDriver.untilFound(() -> By.name("q"));
        autocomplete(q, "ticketfly", webDriver,
                (Browser element) -> element.untilFound(ClassName.SBDD_B)
                        .untilFound(TagName.UL)
                        .findElements(TagName.LI)
                        .filter((WebElement e) -> e.getText().equals("ticketfly")).findFirst()

        );
    }

    @Test
    public void autoCompeleteUsingAutocompleteMethodWithFunctionalProgramming() throws InterruptedException {
        Browser<ChromeDriver> webDriver = Browsers.CHROME;
        webDriver.get("http://google.com");
        Element q = webDriver.untilFound(() -> By.name("q"));
        Locator<Browser<ChromeDriver>, Optional<Element>> locator = new ElementLocator<Browser<ChromeDriver>>(ClassName.SBDD_B)
                .and(new ElementsLocator<>(TagName.LI))
                .and(new FirstMatch<>((Element e) -> e.getText().equals("ticketfly")));
        autocomplete(q, "ticketfly", webDriver, locator);
    }

    @Test
    public void autoCompeleteUsingAutocompleteMethodWithFunctionalProgrammingIllustration() throws InterruptedException {
        Browser<ChromeDriver> webDriver = Browsers.CHROME;
        webDriver.get("http://google.com");
        Element q = webDriver.untilFound(() -> By.name("q"));
        Locator<Browser<ChromeDriver>, Element> browserElementLocator = new ElementLocator<>(ClassName.SBDD_B);
        Locator<Element, Stream<Element>> after = new ElementsLocator<>(TagName.LI);
        Predicate<Element> elementPredicate = GetText.TEXT.and(new Equals("ticketfly"));
        Locator<Stream<Element>, Optional<Element>> ticketfly = new FirstMatch<>(elementPredicate);
        Locator<Browser<ChromeDriver>, Optional<Element>> locator = browserElementLocator.and(after).and(ticketfly);
        autocomplete(q, "ticketfly", webDriver, locator);
    }


    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void autoCompeleteUsingSelenium2() throws InterruptedException {
        Browser<ChromeDriver> webDriver = Browsers.CHROME;
        webDriver.get("http://google.com");
        Element q = webDriver.untilFound(Name.Q);

        Element suggestion = null;
        for (char c : "ticketfly".toCharArray()) {
            q.sendKeys(String.valueOf(c));

            Optional<Element> optionalElement = webDriver.optionalElement(Xpath.TICKETFLY_AUTOCOMPLETE);
            if (optionalElement.isPresent()) {
                suggestion = optionalElement.get();
                suggestion.click();
            }

        }
        if (suggestion == null) {
            suggestion = webDriver.untilFound(Xpath.TICKETFLY_AUTOCOMPLETE);
            suggestion.click();
        }
    }

    public <Where extends SearchScope<Where>> void autocomplete(Element element, Object value, Where where,
                                                                Locator<Where, Optional<Element>> locator) {
        Optional<Element> suggestion;
        for (char c : value.toString().toCharArray()) {
            element.sendKeys(String.valueOf(c));
            suggestion = locator.locate(where);
            if (suggestion.isPresent()) {
                suggestion.get().click();
                return;
            }
        }
        suggestion = locator.locate(where);
        if (suggestion.isPresent()) {
            suggestion.get().click();
        }
    }
}