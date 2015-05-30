package com.algocrafts.chapter2;


import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.security.UserAndPassword;

import java.io.File;
import java.util.List;
import java.util.Set;

import static java.lang.System.out;
import static org.openqa.selenium.By.ByTagName;
import static org.openqa.selenium.WebDriver.*;

public class WebDriverTest {

    @Test
    public void listing21() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://ticketfly.com");
        WebElement element = driver.findElement(By.tagName("a"));
        out.println(element);
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        out.println(elements.size());
        String currentUrl = driver.getCurrentUrl();
        out.println(currentUrl);
        String pageSource = driver.getPageSource();
        out.println(pageSource.substring(0, 100) + "... ...(The rest omitted)");
        String title = driver.getTitle();
        out.println(title);
        String windowHandle = driver.getWindowHandle();
        out.println(windowHandle);
        Set<String> windowHandles = driver.getWindowHandles();
        out.println(windowHandles);
        Options manage = driver.manage();
        out.println(manage);
        Navigation navigate = driver.navigate();
        out.println(navigate);
        TargetLocator targetLocator = driver.switchTo();
        out.println(targetLocator);
        driver.close();
    }

    @Test
    public void listing22() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://ticketfly.com");
        By tag = new ByTagName("input");

        WebElement e = driver.findElement(By.tagName("input"));
        out.println("e.getAttribute(\"value\")=" + e.getAttribute("value"));
        out.println("e.getCssValue(\"width\")=" + e.getCssValue("width"));
        out.println("e.getLocation()=" + e.getLocation());
        out.println("e.getSize()=" + e.getSize());
        out.println("e.getTagName()=" + e.getTagName());
        out.println("e.getText()=" + e.getText());
        out.println("e.isDisplayed()=" + e.isDisplayed());
        out.println("e.isEnabled()=" + e.isEnabled());
        out.println("e.isSelected()=" + e.isSelected());

        e.clear();
        e.sendKeys("I love Selenium");
        out.println("e.getAttribute(\"value\")=" + e.getAttribute("value"));
        e.sendKeys("I love Selenium");
        out.println("e.getAttribute(\"value\")=" + e.getAttribute("value"));
        e.clear();
        out.println("e.getAttribute(\"value\")=" + e.getAttribute("value"));

        driver.quit();
    }


    @Test
    public void listing23() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://jqueryui.com/datepicker/");
        TargetLocator targetLocator = driver.switchTo();
        targetLocator.frame(0);
        driver.findElement(By.id("datepicker")).click();
    }

    @Test
    public void listing24() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.javascripter.net/faq/confirm.htm#top");
        driver.findElements(By.tagName("input")).stream().filter((webElement) -> webElement.getAttribute("value").contains("Try it now")).findFirst().get().click();
        TargetLocator targetLocator = driver.switchTo();
        Alert alert = targetLocator.alert();
        alert.accept();
    }

    @Test
    public void listing25() {
        String path = new File("src/test/resources/html/openWindow.html").getAbsolutePath();
        WebDriver driver = new FirefoxDriver();
        driver.get("file://" + path);
        driver.findElement(By.id("button1")).click();

        Set<String> windowHandles = driver.getWindowHandles();
        out.println(windowHandles);

        TargetLocator targetLocator = driver.switchTo();
        windowHandles.stream().forEach(windowHandle -> {
                    String title = targetLocator.window(windowHandle).getCurrentUrl();
                    out.println("URL for " + windowHandle + " is " + title);
                }

        );

    }

    @Test
    public void listing26() {
        String path = new File("src/test/resources/html/openWindow.html").getAbsolutePath();
        WebDriver driver = new FirefoxDriver();
        driver.get("file://" + path);
        driver.findElement(By.id("button1")).click();
        driver.close();
    }

    @Test
    public void listing27() {
        String path = new File("src/test/resources/html/openWindow.html").getAbsolutePath();
        WebDriver driver = new FirefoxDriver();
        driver.get("file://" + path);
        driver.findElement(By.id("button1")).click();

        driver.quit();

    }

}
