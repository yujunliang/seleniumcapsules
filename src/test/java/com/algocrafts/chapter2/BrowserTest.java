package com.algocrafts.chapter2;


import com.algocrafts.chapter2.factory.BetterWebDriverFactory;
import com.algocrafts.chapter2.factory.NaiveWebDriverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.EnumSet;

import static com.algocrafts.browsers.Browsers.*;

public class BrowserTest {

    @Test
    public void openEmptyFirefox() {
        new FirefoxDriver();
    }

    @Test
    public void findHowItWorksLinkUsingFirefoxt() {
        WebDriver webDriver = NaiveWebDriverFactory.getInstance("firefox");
        webDriver.get("http://ticketfly.com/careers");
        WebElement element = webDriver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingChromet() {
        WebDriver webDriver = NaiveWebDriverFactory.getInstance("chrome");
        webDriver.get("http://ticketfly.com/careers");
        WebElement element = webDriver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingSafari() {
        WebDriver webDriver = NaiveWebDriverFactory.getInstance("safari");
        webDriver.get("http://ticketfly.com/careers");
        WebElement element = webDriver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingSafariEnum() {
        WebDriver webDriver = BetterWebDriverFactory.SAFARI.get();
        webDriver.get("http://ticketfly.com/careers");
        WebElement element = webDriver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingAllBrowsers() {
        for (WebDriver webDriver : EnumSet.of(CHROME, FIREFOX, SAFARI)) {
            webDriver.get("http://ticketfly.com/careers");
            WebElement element = webDriver.findElement(By.linkText("How It Works"));
            System.out.println("element=" + element);
            System.out.println("element.getTagName()=" + element.getTagName());
            System.out.println("element.getText()=" + element.getText());
        }
    }


}


