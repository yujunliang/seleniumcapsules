package com.algocrafts.chapter1;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserTest {

    @Test
    public void openEmptyFirefox() {
        new FirefoxDriver();
    }

    @Test
    public void findHowItWorksLinkUsingLinkText() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://ticketfly.com/careers");
        WebElement element = webDriver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingPartialLinkText() {
        WebDriver webDriver = new SafariDriver();
        webDriver.get("http://ticketfly.com/careers");
        WebElement element = webDriver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingPartialLinkText1() {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://ticketfly.com/careers");
        WebElement element = webDriver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

}


