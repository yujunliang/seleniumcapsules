package com.algocrafts.chapter3;

import com.algocrafts.chapter2.factory.BetterWebDriverFactory;
import com.algocrafts.selectors.*;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Element;
import com.google.common.base.Function;
import com.ticketfly.TicketflyPage;
import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.selectors.LinkText.*;
import static com.algocrafts.selectors.Name.FILTER_EVENT;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.openqa.selenium.By.*;

public class TicketflyTest {

    StopWatch stopWatch = new StopWatch();

    @Before
    public void startStopWatch() {
        stopWatch.start();
    }

    @After
    public void print() {
        System.out.println("Time taken " + stopWatch);
    }

    /**
     * This test will fail.
     */
    @Test
    public void changeLocationUsingSelenium() {
        WebDriver webDriver = BetterWebDriverFactory.CHROME.get();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(By.linkText("change location")).click();
        WebElement location = webDriver.findElement(By.id("location"));
        location.findElement(By.linkText("CANADA")).click();
        WebElement element = location.findElement(By.linkText("Ontario"));
        element.click();
        assertEquals(0, location.findElements(By.linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(By.xpath("div[@class='tools-location']/descendant::strong")).getText());

    }


    @Test
    public void changeLocationWithImplicitWait() {
        WebDriver webDriver = BetterWebDriverFactory.CHROME.get();
        webDriver.manage().timeouts().implicitlyWait(30, SECONDS);
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(By.linkText("change location")).click();
        WebElement tabMenu = webDriver.findElement(By.id("location"));
        tabMenu.findElement(By.linkText("CANADA")).click();
        WebElement element = tabMenu.findElement(By.linkText("Ontario"));
        element.click();
        assertEquals(0, tabMenu.findElements(By.linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(By.xpath("div[@class='tools-location']/descendant::strong")).getText());

    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void changeLocationUsingExplicitWait() {
        WebDriver webDriver = BetterWebDriverFactory.CHROME.get();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);

        WebElement location = webDriverWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("location"));
            }
        });

        FluentWait<WebElement> webElementWait
                = new FluentWait<WebElement>(location)
                .withTimeout(30, SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement canada = webElementWait.until(new Function<WebElement, WebElement>() {
            @Override
            public WebElement apply(WebElement element) {
                return location.findElement(linkText("CANADA"));
            }
        });
        canada.click();
        WebElement allCanada = webElementWait.until(new Function<WebElement, WebElement>() {
            @Override
            public WebElement apply(WebElement element) {
                return location.findElement(linkText("Ontario"));
            }
        });
        allCanada.click();
        assertEquals(0, location.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(By.xpath("//div[@class='tools']/descendant::strong")).getText());
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void changeLocationUsingExplicitWaitLambda() {
        WebDriver webDriver = BetterWebDriverFactory.CHROME.get();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();
        FluentWait<WebDriver> webDriverWait
                = new FluentWait<WebDriver>(webDriver)
                .ignoring(NoSuchElementException.class);
        WebElement location = webDriverWait.until(
                (WebDriver driver) ->
                        driver.findElement(By.id("location"))

        );

        FluentWait<WebElement> webElementWait
                = new FluentWait<>(location)
                .withTimeout(30, SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement canada = webElementWait.until(
                (WebElement element) ->
                        element.findElement(linkText("CANADA")));
        canada.click();
        WebElement ontario = webElementWait.until(
                (WebElement element) ->
                        element.findElement(linkText("Ontario")));
        ontario.click();
        assertEquals("Ontario", webDriver
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
        browser.untilFound(LinkText.CHANGE_LOCATION).click();
        Element tabMenu = browser.untilFound(Id.LOCATION);
        tabMenu.untilFound(LinkText.CANADA).click();
        tabMenu.untilFound(LinkText.ONTARIO).click();
        assertFalse(tabMenu.optionalElement(LinkText.ONTARIO).isPresent());
        assertEquals("Ontario", browser.untilFound(Xpath.LOCATION).getText());
    }

    /**
     * This is a cleaner test using page framework. It has the same function as the test above. :)
     */
    @Test
    public void changeLocation() {
        TicketflyPage page = new TicketflyPage(CHROME);
        page.open();
        page.changeLocation(CANADA, ONTARIO);
        assertEquals("Ontario", page.currentLocation());
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