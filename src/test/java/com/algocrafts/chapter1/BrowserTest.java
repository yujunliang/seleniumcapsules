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
        WebDriver driver = new ChromeDriver();
        driver.get("http://ticketfly.com/careers");
        WebElement element = driver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingPartialLinkText() {
        WebDriver driver = new SafariDriver();
        driver.get("http://ticketfly.com/careers");
        WebElement element = driver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

    @Test
    public void findHowItWorksLinkUsingPartialLinkText1() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://ticketfly.com/careers");
        WebElement element = driver.findElement(By.linkText("How It Works"));
        System.out.println("element=" + element);
        System.out.println("element.getTagName()=" + element.getTagName());
        System.out.println("element.getText()=" + element.getText());
    }

}


