package com.algocrafts.chapter1;


import com.algocrafts.selectors.LinkText;
import com.algocrafts.selenium.Browser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.algocrafts.browsers.Browsers.CHROME;

public class LinkClickingTest {

    @Test
    public void openChromeAndClickJavaLink() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver");
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("http://manning.com");
        chromeDriver.findElement(By.linkText("Java")).click();
    }

    @Test
    public void openChromeAndClickJavaLinkUsingSeleniumCapsules() {
        Browser browser = CHROME;
        browser.get("http://manning.com");
        browser.link(LinkText.JAVA).click();
    }

}


