package com.algocrafts.chapter2;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * Created by yujunliang on 8/31/14.
 */
public class TargetLocatorTest {

    @Test
    public void switchFrameUsingIndex() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("file://" + file.getAbsolutePath());

        webDriver.switchTo().frame(0);
        System.out.println("frame(0)=" + webDriver.getCurrentUrl());

        webDriver.switchTo().defaultContent();

        webDriver.switchTo().frame(1);
        System.out.println("frame(1)=" + webDriver.getCurrentUrl());
    }

    @Test
    public void switchFrameUsingNameOrId() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("file://" + file.getAbsolutePath());

        webDriver.switchTo().frame("Demo1");
        System.out.println("frame(\"Demo1\")=" + webDriver.getCurrentUrl());

        webDriver.switchTo().defaultContent();

        webDriver.switchTo().frame("Demo2");
        System.out.println("frame(\"Demo2\")=" + webDriver.getCurrentUrl());

        webDriver.switchTo().defaultContent();

        webDriver.switchTo().frame("iframe1");
        System.out.println("frame(\"iframe1\")=" + webDriver.getCurrentUrl());

        webDriver.switchTo().defaultContent();

        webDriver.switchTo().frame("iframe2");
        System.out.println("frame(\"iframe2\")=" + webDriver.getCurrentUrl());
    }


    @Test
    public void switchFrameUsingWebElement() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("file://" + file.getAbsolutePath());

        WebElement element = webDriver.findElement(By.id("iframe1"));

        webDriver.switchTo().frame(element);
        System.out.println("frame(\"iframe1\")=" + webDriver.getCurrentUrl());
    }

    @Test
    public void switchBackToParentFrame() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("file://" + file.getAbsolutePath());
        webDriver.switchTo().parentFrame();
        System.out.println(webDriver.getCurrentUrl());

        WebElement element = webDriver.findElement(By.id("iframe1"));

        webDriver.switchTo().frame(element);

        webDriver.switchTo().parentFrame();
        System.out.println(webDriver.getCurrentUrl());

        webDriver.switchTo().frame(1).switchTo().frame(0);
        webDriver.switchTo().parentFrame();
        System.out.println(webDriver.getCurrentUrl());


    }

    @Test
    public void switchBackToDefaultContent() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("file://" + file.getAbsolutePath());
        webDriver.switchTo().defaultContent();
        System.out.println(webDriver.getCurrentUrl());

        WebElement element = webDriver.findElement(By.id("iframe1"));

        webDriver.switchTo().frame(element);

        webDriver.switchTo().defaultContent();
        System.out.println(webDriver.getCurrentUrl());

        webDriver.switchTo().frame(1).switchTo().frame(0);
        webDriver.switchTo().defaultContent();
        System.out.println(webDriver.getCurrentUrl());
    }

    @Test
    public void switchToActiceElement() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("file://" + file.getAbsolutePath());
        webDriver.switchTo().frame(0);

        WebElement webElement = webDriver.switchTo().activeElement();
        String text = webElement.getText();
        System.out.println(text.substring(0, 100) + "... ...("+ (text.length() -100) +" characters omitted)");
    }

    @Test
    public void alert() throws InterruptedException {
        File file = new File("src/test/resources/html/alert.html");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("file://" + file.getAbsolutePath());
        webDriver.findElement(By.id("button1")).click();

        Alert alert = webDriver.switchTo().alert();
        System.out.println(alert);
    }
}
