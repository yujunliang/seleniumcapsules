package com.algocrafts.chapter2;


import com.algocrafts.selenium.Browser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.selectors.CssSelector.NEED_CONIRM;
import static org.openqa.selenium.By.cssSelector;

public class ConfirmTest {

    @Test
    public void clickConfirmUsingSelenium() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:63342/seleniumcapsules/html/upload.html");
        driver.findElement(cssSelector("input[value='Need Confirm']")).click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void clickConfirm() {
        Browser browser = CHROME;
        browser.get("http://localhost:63342/seleniumcapsules/html/upload.html");
        browser.button(NEED_CONIRM).click();
        browser.accept();
    }

}


