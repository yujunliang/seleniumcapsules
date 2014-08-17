package com.algocrafts.chapter1;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.selectors.LinkText;
import com.algocrafts.selenium.Browser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.algocrafts.browsers.Browsers.CHROME;

public class BrowserTest {

    @Test
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver");
        new ChromeDriver();
        System.out.println("First");
    }

    @Test
    public void notOpenChromeUsingSeleniumCapsules() {
        Browser browser = CHROME;
        System.out.println("Second");
    }

    @Test
    public void openChromeUsingSeleniumCapsules() {
        Browser browser = CHROME;
        browser.get("http://ticketfly.com");
        System.out.println("Third");
    }

    @Test
    public void findChangeLocationLinkUsingSelenium() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://ticketfly.com/events");
        WebElement element = webDriver.findElement(By.linkText("change location"));
        System.out.println("element=" + element);
        System.out.println("element.getClass()=" + element.getClass());
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findChangeLocationLinkUsingSeleniumCapsules() {
        Browser webDriver = Browsers.CHROME;
        webDriver.get("http://ticketfly.com/events");
        WebElement element = webDriver.untilFound(LinkText.CHANGE_LOCATION);
        System.out.println("element=" + element);
        System.out.println("element.getClass()=" + element.getClass());
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void openChromeAndClickJavaLinkUsingSeleniumCapsules() {
        Browser browser = CHROME;
        browser.get("http://ticketfly.com/events");
    }


}


