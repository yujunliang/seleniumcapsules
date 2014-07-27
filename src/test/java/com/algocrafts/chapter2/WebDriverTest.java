package com.algocrafts.chapter2;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverTest {

    @Test
    public void startFirefox() throws InterruptedException {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://google.com");

    }
}
