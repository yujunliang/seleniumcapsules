package com.algocrafts.chapter2;

import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by yujunliang on 8/31/14.
 */
public class TimeoutsTest {

    private StopWatch stopWatch = new StopWatch();
    @Before
    public void startStopWatch() {
        stopWatch.start();
    }

    @After
    public void print() {
        System.out.println("Taken " + stopWatch);
    }

    /**
     * This test will fail.
     */
    @Test
    public void failedTest() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(By.linkText("change location")).click();
        webDriver.findElement(By.linkText("CANADA")).click();
        WebElement element = webDriver.findElement(By.linkText("All Canada"));
        element.click();
        assertEquals(0, webDriver.findElements(By.linkText("All Canada")).size());
        assertEquals("Canada", webDriver
                .findElement(By.className("tools-location"))
                .findElement(By.tagName("a"))
                .findElement(By.tagName("strong"))
                .getText());

    }


    @Test
    public void implicitlyWait() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(By.linkText("change location")).click();
        webDriver.findElement(By.linkText("CANADA")).click();
        WebElement element = webDriver.findElement(By.linkText("All Canada"));
        element.click();
        assertEquals(0, webDriver.findElements(By.linkText("All Canada")).size());
        assertEquals("Canada", webDriver
                .findElement(By.className("tools-location"))
                .findElement(By.tagName("a"))
                .findElement(By.tagName("strong"))
                .getText());

    }

    @Test
    public void setScriptTimeout() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeAsyncScript("alert('Hi')");
    }

    @Test
    public void pageLoadTimeout() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.get("http://www.ticketfly.com");
    }


}
