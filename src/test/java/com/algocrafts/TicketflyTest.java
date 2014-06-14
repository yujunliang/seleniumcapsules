package com.algocrafts;

import com.algocrafts.locators.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import com.google.common.base.Function;
import com.ticketfly.TicketflyPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    /**
     * This test will fail.
     */
    @Test
    public void changeLocationUsingSelenium() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();
        webDriver.findElement(linkText("CANADA")).click();
        webDriver.findElement(linkText("All Canada")).click();
        assertEquals("Canada", webDriver
                .findElement(By.className("tools-location"))
                .findElement(By.tagName("a"))
                .findElement(By.tagName("strong"))
                .getText());
    }

    @Test
    public void changeLocationUsingSeleniumWithExplicitWait() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
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
        assertEquals("Canada", webDriver
                .findElement(By.className("tools-location"))
                .findElement(By.tagName("a"))
                .findElement(By.tagName("strong"))
                .getText());
    }


    @Test
    public void changeLocationUsingBrowser() {
        Browser browser = CHROME;
        browser.get("http://www.ticketfly.com");
        browser.link(CHANGE_LOCATION).click();
        browser.link(CANADA).click();
        browser.link(ALL_CANADA).click();

        assertEquals("Canada", Locators.<AbstractPage>element(TOOLS_LOCATION)
                .and(element(A))
                .and(element(STRONG))
                .and(TEXT).locate(new Page(browser)));
    }

    @Test
    public void changeLocation() {
        TicketflyPage page = new TicketflyPage(CHROME);
        page.open();
        page.changeLocation(CANADA, ALL_CANADA);

        assertEquals("Canada", page.currentLocation());
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
