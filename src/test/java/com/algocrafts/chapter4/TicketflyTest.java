package com.algocrafts.chapter4;

import com.algocrafts.browsers.Browsers;
import com.algocrafts.chapter2.factory.BetterWebDriverFactory;
import com.algocrafts.chapter3.TicketflyPage;
import com.algocrafts.selectors.Id;
import com.algocrafts.selectors.LinkText;
import com.algocrafts.selectors.Xpath;
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
import static com.algocrafts.selectors.LinkText.CANADA;
import static com.algocrafts.selectors.LinkText.ONTARIO;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.linkText;

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

    @Test
    public void findChangeLocationUsingClassName() {
        WebDriver webDriver = Browsers.CHROME;
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(By.className("change-location")).click();
    }


    @Test
    public void findChangeLocationUsingId() {
        WebDriver webDriver = Browsers.CHROME;
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(By.id("change-location")).click();

    }

    @Test
    public void findChangeLocationUsingTagName() {
        WebDriver webDriver = Browsers.CHROME;
        webDriver.get("http://www.ticketfly.com");
        Stream<WebElement> links = webDriver.findElements(By.tagName("a")).stream();
        links.filter((WebElement e) -> e.getText().equals("change location")).findFirst().get().click();
    }

    @Test
    public void findChangeLocationUsingPartialLinkText() {
        WebDriver webDriver = Browsers.CHROME;
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(By.partialLinkText("change")).click();
    }

    @Test
    public void findChangeLocationUsingLinkText() {
        WebDriver webDriver = Browsers.CHROME;
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(By.linkText("change location")).click();
    }

    @Test
    public void findChangeLocationUsingCssSelecotr() {
        WebDriver webDriver = Browsers.CHROME;
        webDriver.get("http://www.ticketfly.com");
      System.out.println(webDriver.findElement(By.cssSelector(".active")).toString());
    }

    @Test
    public void findChangeLocationUsingXpath() {
        WebDriver webDriver = Browsers.CHROME;
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(By.xpath("//a[@id='change-location']")).click();
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void changeLocationUsingExplicitWaitLambda() {
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
        assertEquals(0, webDriver.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(By.xpath("//div[@class='tools']/descendant::strong")).getText());
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
            changeLocation(CANADA, ONTARIO);
            assertEquals("Canada", currentLocation());
        }};
    }

    @Test
    public void discoverMoreEventUsingSelenium() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("Discover More Events")).click();
        webDriver.findElement(id("filter-events3")).click();
    }

}
