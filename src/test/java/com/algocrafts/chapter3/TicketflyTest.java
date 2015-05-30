package com.algocrafts.chapter3;

import com.algocrafts.chapter2.factory.BetterWebDriverFactory;
import com.algocrafts.selectors.*;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Element;
import com.google.common.base.Function;
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

import java.util.stream.Stream;

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
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.ticketfly.com");
        driver.findElement(By.linkText("change location")).click();       //<1>
        WebElement location = driver.findElement(By.id("location"));
        location.findElement(By.linkText("CANADA")).click();
        WebElement element = location.findElement(By.linkText("Ontario"));
        element.click();
        assertEquals(0, location.findElements(By.linkText("Ontario")).size()); //<2>
        assertEquals("Ontario", driver   //<3>
                .findElement(
                        By.xpath("div[@class='tools-location']/descendant::strong")
                )
                .getText());

    }


    @Test
    public void changeLocationWithImplicitWait() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);     //<1>
        driver.get("http://www.ticketfly.com");
        driver.findElement(By.linkText("change location")).click();
        WebElement tabMenu = driver.findElement(By.id("location"));
        tabMenu.findElement(By.linkText("CANADA")).click();
        WebElement element = tabMenu.findElement(By.linkText("Ontario"));
        element.click();
        assertEquals(0, tabMenu.findElements(By.linkText("Ontario")).size());
        assertEquals("Ontario", driver   //<2>
                .findElement(
                        By.xpath("div[@class='tools-location']/descendant::strong")
                )
                .getText());

    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void changeLocationUsingExplicitWait() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.ticketfly.com");
        driver.findElement(linkText("change location")).click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);  //<1>

        WebElement location = webDriverWait.until(
                new Function<WebDriver, WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.id("location"));
                    }
                }
        );

        FluentWait<WebElement> webElementWait              //<2>
                = new FluentWait<WebElement>(location)
                .withTimeout(30, SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement canada = webElementWait.until(
                new Function<WebElement, WebElement>() {
                    @Override
                    public WebElement apply(WebElement element) {
                        return location.findElement(linkText("CANADA"));
                    }
                }
        );
        canada.click();
        WebElement allCanada = webElementWait.until(
                new Function<WebElement, WebElement>() {
                    @Override
                    public WebElement apply(WebElement element) {
                        return location.findElement(linkText("Ontario"));
                    }
                }
        );
        allCanada.click();
        assertEquals(0, driver.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", driver
                .findElement(By.xpath("//div[@class='tools']/descendant::strong")).getText());
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void changeLocationUsingExplicitWaitLambda() {
        WebDriver driver = BetterWebDriverFactory.CHROME.get();
        driver.get("http://www.ticketfly.com");
        driver.findElement(linkText("change location")).click();
        FluentWait<WebDriver> webDriverWait
                = new FluentWait<WebDriver>(driver)
                .ignoring(NoSuchElementException.class);
        WebElement location = webDriverWait.until(
                (WebDriver d) ->
                        d.findElement(By.id("location"))

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
        assertEquals("Ontario", driver
                .findElements(By.tagName("p")).stream().filter((WebElement e) -> e.getAttribute("class").equals("tools-location"))
                .findFirst().get()
                .findElement(By.tagName("a"))
                .findElement(By.tagName("strong"))
                .getText());
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void changeLocationUsingExplicitWaitLambda1() {
        WebDriver driver = BetterWebDriverFactory.CHROME.get();
        driver.get("http://www.ticketfly.com");
        driver.findElement(linkText("change location")).click();
        FluentWait<WebDriver> webDriverWait
                = new FluentWait<>(driver)
                .ignoring(NoSuchElementException.class);
        WebElement location = webDriverWait.until(
                (WebDriver d) ->
                        d.findElement(By.id("location"))

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
        Stream<WebElement> pStream = driver.findElements(By.tagName("p")).stream();
        Stream<WebElement> filteredStream = pStream.filter((WebElement e) -> e.getAttribute("class").equals("tools-location"));
        WebElement element = filteredStream.findFirst().get();
        WebElement a = element.findElement(By.tagName("a"));
        WebElement strong = a.findElement(By.tagName("strong"));

        assertEquals("Ontario", strong
                .getText());
    }

    /**
     * This is a clean test using page framework. It has the same function as the test above.  :)
     */
    @Test
    public void changeLocationUsingBrowser() {
        Browser browser = CHROME;
        browser.get("http://www.ticketfly.com");
        browser.untilFound(LinkText.CHANGE_LOCATION).click();   //<1>
        Element tabMenu = browser.untilFound(Id.LOCATION);
        tabMenu.untilFound(LinkText.CANADA).click();
        tabMenu.untilFound(LinkText.ONTARIO).click();
        assertFalse(tabMenu.optionalElement(LinkText.ONTARIO).isPresent());     //<2>
        assertEquals("Ontario", browser.untilFound(Xpath.LOCATION).getText());
    }

    /**
     * This is a cleaner test using page framework. It has the same function as the test above. :)
     */
    @Test
    public void changeLocation() {
        TicketflyPage page = new TicketflyPage(CHROME);
        page.open();
        page.changeLocation(CANADA, ONTARIO);        //<1>
        assertEquals("Ontario", page.currentLocation());
    }

    /**
     * This is a cleaner test using page framework. It has the same function as the test above. :)
     */
    @Test
    public void changeLocationAnonymous() {
        new TicketflyPage(CHROME) {{
            open();
            changeLocation(CANADA, ONTARIO);
            assertEquals("Ontario", currentLocation());
        }};
    }

    @Test
    public void discoverMoreEventUsingSelenium() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.ticketfly.com");
        driver.findElement(linkText("Discover More Events")).click();
        driver.findElement(id("filter-events3")).click();
    }

}
