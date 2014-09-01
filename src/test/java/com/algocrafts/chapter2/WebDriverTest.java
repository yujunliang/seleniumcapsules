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
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://ticketfly.com");
        WebElement element = webDriver.findElement(By.tagName("a"));
        out.println(element);
        List<WebElement> elements = webDriver.findElements(By.tagName("a"));
        out.println(elements.size());
        String currentUrl = webDriver.getCurrentUrl();
        out.println(currentUrl);
        String pageSource = webDriver.getPageSource();
        out.println(pageSource.substring(0, 100) + "... ...(The rest omitted)");
        String title = webDriver.getTitle();
        out.println(title);
        String windowHandle = webDriver.getWindowHandle();
        out.println(windowHandle);
        Set<String> windowHandles = webDriver.getWindowHandles();
        out.println(windowHandles);
        Options manage = webDriver.manage();
        out.println(manage);
        Navigation navigate = webDriver.navigate();
        out.println(navigate);
        TargetLocator targetLocator = webDriver.switchTo();
        out.println(targetLocator);
        webDriver.close();
    }

    @Test
    public void listing22() throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://ticketfly.com");
        By tag = new ByTagName("input");

        WebElement e = webDriver.findElement(By.tagName("input"));
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

        webDriver.quit();
    }


    @Test
    public void listing23() throws InterruptedException {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://jqueryui.com/datepicker/");
        TargetLocator targetLocator = webDriver.switchTo();
        targetLocator.frame(0);
        webDriver.findElement(By.id("datepicker")).click();
    }

    @Test
    public void listing24() throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.javascripter.net/faq/confirm.htm#top");
        webDriver.findElements(By.tagName("input")).stream().filter((webElement) -> webElement.getAttribute("value").contains("Try it now")).findFirst().get().click();
        TargetLocator targetLocator = webDriver.switchTo();
        Alert alert = targetLocator.alert();
        alert.accept();
    }

    @Test
    public void listing25() {
        String path = new File("src/test/resources/html/openWindow.html").getAbsolutePath();
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("file://" + path);
        webDriver.findElement(By.id("button1")).click();

        Set<String> windowHandles = webDriver.getWindowHandles();
        out.println(windowHandles);

        TargetLocator targetLocator = webDriver.switchTo();
        windowHandles.stream().forEach(windowHandle -> {
                    String title = targetLocator.window(windowHandle).getCurrentUrl();
                    out.println("URL for " + windowHandle + " is " + title);
                }

        );

    }

    @Test
    public void listing26() {
        String path = new File("src/test/resources/html/openWindow.html").getAbsolutePath();
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("file://" + path);
        webDriver.findElement(By.id("button1")).click();
        webDriver.close();
    }

    @Test
    public void listing27() {
        String path = new File("src/test/resources/html/openWindow.html").getAbsolutePath();
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("file://" + path);
        webDriver.findElement(By.id("button1")).click();

        webDriver.quit();

    }

}
