package com.algocrafts.chapter3;


import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

@Ignore
public class ActionsTest {

    @Test
    public void keyDown() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.ticketfly.com");

        Actions actions= new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//body"));
        actions.keyDown(element, Keys.COMMAND).sendKeys(element,"a").perform();

    }
}
