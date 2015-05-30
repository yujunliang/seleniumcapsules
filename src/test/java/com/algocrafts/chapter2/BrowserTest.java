package com.algocrafts.chapter2;


import com.algocrafts.chapter2.factory.BetterWebDriverFactory;
import com.algocrafts.chapter2.factory.NaiveWebDriverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.EnumSet;
import java.util.stream.Stream;

import static com.algocrafts.browsers.Browsers.*;
import static com.google.common.collect.Lists.newArrayList;

public class BrowserTest {

    @Test
    public void openEmptyFirefox() {
        new FirefoxDriver();
    }

    @Test
    public void findHowItWorksLinkUsingFirefoxt() {
        WebDriver driver = NaiveWebDriverFactory.getInstance("firefox");
        driver.get("http://ticketfly.com/careers");
        WebElement element = driver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingChromet() {
        WebDriver driver = NaiveWebDriverFactory.getInstance("chrome");
        driver.get("http://ticketfly.com/careers");
        WebElement element = driver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingSafari() {
        WebDriver driver = NaiveWebDriverFactory.getInstance("safari");
        driver.get("http://ticketfly.com/careers");
        WebElement element = driver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingSafariEnum() {
        WebDriver driver = BetterWebDriverFactory.SAFARI.get();
        driver.get("http://ticketfly.com/careers");
        WebElement element = driver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingAllBrowsers() {
        for (WebDriver driver : newArrayList(CHROME.init(),CHROME.init(), FIREFOX, SAFARI)) {
            driver.get("http://ticketfly.com/careers");
            WebElement element = driver.findElement(By.linkText("How It Works"));
            System.out.println("element=" + element);
            System.out.println("element.getTagName()=" + element.getTagName());
            System.out.println("element.getText()=" + element.getText());
        }
    }


}


