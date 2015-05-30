package com.algocrafts.chapter2;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import static org.openqa.selenium.WebDriver.Options;

/**
 * Created by yujunliang on 8/31/14.
 */
public class ImeHandlerTest {

    @Test
    public void getAvailableEngines() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://ticketfly.com");

        Options manage = driver.manage();
        WebDriver.ImeHandler ime = manage.ime();
        ime.getAvailableEngines();
    }
}
