package com.algocrafts.chapter2;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverTest {

    @Test
    public void listing21() throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();

    }

    @Test
    public void listing22() throws InterruptedException {
        WebDriver webDriver = new FirefoxDriver();

    }

    @Test
    public void listing23() throws InterruptedException {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://google.com");
    }

}
