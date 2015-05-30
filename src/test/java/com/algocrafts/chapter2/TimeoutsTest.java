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
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.ticketfly.com");
        driver.findElement(By.linkText("change location")).click();
        driver.findElement(By.linkText("CANADA")).click();
        WebElement element = driver.findElement(By.linkText("All Canada"));
        element.click();
        assertEquals(0, driver.findElements(By.linkText("All Canada")).size());
        assertEquals("Canada", driver
                .findElements(By.tagName("p")).stream().filter((WebElement e) -> e.getAttribute("class").equals("tools-location"))
                .findFirst().get()
                .findElement(By.tagName("a"))
                .findElement(By.tagName("strong"))
                .getText());

    }


    @Test
    public void implicitlyWait() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://www.ticketfly.com");
        driver.findElement(By.linkText("change location")).click();
        driver.findElement(By.linkText("CANADA")).click();
        WebElement element = driver.findElement(By.linkText("All Canada"));
        element.click();
        assertEquals(0, driver.findElements(By.linkText("All Canada")).size());
        assertEquals("Canada", driver
                .findElement(By.className("tools-location"))
                .findElement(By.tagName("a"))
                .findElement(By.tagName("strong"))
                .getText());

    }

    @Test
    public void setScriptTimeout() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.ticketfly.com");
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeAsyncScript("alert('Hi')");
    }

    @Test
    public void pageLoadTimeout() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("http://www.ticketfly.com");
    }


}
