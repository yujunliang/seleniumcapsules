package com.algocrafts;

import com.algocrafts.selenium.Browser;
import com.google.common.base.Function;
import com.ticketfly.TicketflyHomePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.selectors.LinkText.*;
import static com.algocrafts.selectors.Name.FILTER_EVENT;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.linkText;

public class TicketflyTest {

    @Test
    public void changeLocationUsingBrowser() {
        Browser browser = CHROME;
        browser.get("http://www.ticketfly.com");
        browser.link(CHANGE_LOCATION).click();
        browser.link(CANADA).click();
        browser.link(ALL_CANADA).click();
    }

    @Test
    public void changeLocation() {
        TicketflyHomePage page = new TicketflyHomePage(CHROME);
        page.open();
        page.changeLocation(CANADA, ALL_CANADA);
    }

    @Test
    public void changeLocationUsingSelenium() {
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
    }


    @Test
    public void discoverMoreEvent() {
        TicketflyHomePage page = new TicketflyHomePage(CHROME);
        page.open();
        page.discoverMoreEvent();
        page.setRadio(FILTER_EVENT, "Just Announced");
    }

    @Test
    public void discoverMoreEventAnonymousClass() {
        new TicketflyHomePage(CHROME) {{
            open();
            discoverMoreEvent();
            setRadio(FILTER_EVENT, "Just Announced");
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
}
