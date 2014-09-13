package com.algocrafts.chapter3;

import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import com.google.common.base.Function;
import com.ticketfly.TicketflyPage;
import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.ClassName.TOOLS_LOCATION;
import static com.algocrafts.selectors.LinkText.*;
import static com.algocrafts.selectors.Name.FILTER_EVENT;
import static com.algocrafts.selectors.TagName.A;
import static com.algocrafts.selectors.TagName.STRONG;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.*;

public class TicketflyTest {

    StopWatch stopWatch = new StopWatch();
    @Before
    public void startStopWatch() {
        stopWatch.start();
    }

    @After
    public void print() {
        System.out.println("Taken " + stopWatch);
    }

/**
 * This test will fail.
 */
@Test
public void changeLocationUsingSelenium() {
    WebDriver webDriver = new ChromeDriver();
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    webDriver.get("http://www.ticketfly.com");
    webDriver.findElement(By.linkText("change location")).click();
    webDriver.findElement(By.linkText("CANADA")).click();
    WebElement element = webDriver.findElement(By.linkText("All Canada"));
    element.click();
    assertEquals(0, webDriver.findElements(By.linkText("All Canada")).size());
    assertEquals("Canada", webDriver
            .findElement(By.className("tools-location"))
            .findElement(By.tagName("a"))
            .findElement(By.tagName("strong"))
            .getText());

}

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void changeLocationUsingSeleniumWithWebDriverWait() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        Function<WebDriver, WebElement> canadaFinder = new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(linkText("CANADA"));
            }
        };
        WebElement canada = wait.until(canadaFinder);
        canada.click();
        WebElement allCanada = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(linkText("All Canada"));
            }
        });
        allCanada.click();
        assertEquals(0, webDriver.findElements(linkText("All Canada")).size());
        assertEquals("Canada", webDriver
                .findElement(className("tools-location"))
                .findElement(tagName("a"))
                .findElement(tagName("strong"))
                .getText());
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void changeLocationUsingSeleniumWithFluentWait() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout( 5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).pollingEvery(50, TimeUnit.MILLISECONDS);
        WebElement canada = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(linkText("CANADA"));
            }
        });
        canada.click();
        WebElement allCanada = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(linkText("All Canada"));
            }
        });
        allCanada.click();
        assertEquals(0, webDriver.findElements(linkText("All Canada")).size());
        assertEquals("Canada", webDriver
                .findElement(className("tools-location"))
                .findElement(tagName("a"))
                .findElement(tagName("strong"))
                .getText());
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void changeLocationUsingSeleniumWithExplicitWaitLambda() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        WebElement canada = wait.until((WebDriver o) -> webDriver.findElement(linkText("CANADA")));
        canada.click();
        WebElement allCanada = wait.until((WebDriver o) -> webDriver.findElement(linkText("All Canada")));
        allCanada.click();
        assertEquals("Canada", webDriver
                .findElement(className("tools-location"))
                .findElement(tagName("a"))
                .findElement(tagName("strong"))
                .getText());
    }

    /**
     * This is a clean test using page framework. It has the same function as the test above.  :)
     */
    @Test
    public void changeLocationUsingBrowser() {
        Browser browser = CHROME;
        browser.get("http://www.ticketfly.com");
        browser.link(CHANGE_LOCATION).click();
        browser.link(CANADA).click();
        browser.link(ALL_CANADA).click();

        assertEquals("Canada", Locators.<Page>element(TOOLS_LOCATION)
                .andThen(element(A))
                .andThen(element(STRONG))
                .andThen(TEXT)
                .locate(new Page(browser)));
    }

    /**
     * This is a cleaner test using page framework. It has the same function as the test above. :)
     */
    @Test
    public void changeLocation() {
        TicketflyPage page = new TicketflyPage(CHROME);
        page.open();
        page.changeLocation(CANADA, ALL_CANADA);
        assertEquals("Canada", page.currentLocation());
    }

    /**
     * This is a cleaner test using page framework. It has the same function as the test above. :)
     */
    @Test
    public void changeLocationAnonymous() {
        new TicketflyPage(CHROME) {{
            open();
            changeLocation(CANADA, ALL_CANADA);
            assertEquals("Canada", currentLocation());
        }};
    }

    @Test
    public void discoverMoreEventUsingSelenium() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("Discover More Events")).click();
        webDriver.findElement(id("filter-events3")).click();
    }

    @Test
    public void discoverMoreEvent() {
        TicketflyPage page = new TicketflyPage(CHROME);
        page.open();
        page.discoverMoreEvent();
        page.setRadio(FILTER_EVENT, "Just Announced");
    }

    @Test
    public void discoverMoreEventAnonymousClass() {
        new TicketflyPage(CHROME) {{
            open();
            discoverMoreEvent();
            setRadio(FILTER_EVENT, "Just Announced");
        }};
    }

}
