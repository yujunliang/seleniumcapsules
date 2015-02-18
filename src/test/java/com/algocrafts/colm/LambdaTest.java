package com.algocrafts.colm;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LambdaTest {

    @Test
    public void testForColm() {
        WebDriver driver = new FirefoxDriver();
        final String LATE_LOADING_TEXT = "Hello World!";
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        WebElement element = driver.findElement(By.id("start")).findElement(By.tagName("button"));
        element.click();

// returns a boolean
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("finish")).findElement(By.tagName("h4")).getText().equals(LATE_LOADING_TEXT);
            }
        });

// lambda doesn't work
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.findElement(By.id("finish")).findElement(By.tagName("h4")).getText().equals(LATE_LOADING_TEXT));

// returns a webelement
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(By.id("finish")).findElement(By.tagName("h4"));
            }
        });

// lambda is ok
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.findElement(By.id("finish")).findElement(By.tagName("h4")));


    }


}


