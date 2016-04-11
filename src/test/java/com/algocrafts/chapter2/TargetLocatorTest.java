package com.algocrafts.chapter2;

import org.junit.Ignore;
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
@Ignore
public class TargetLocatorTest {

    @Test
    public void switchFrameUsingIndex() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver driver = new ChromeDriver();
        driver.get("file://" + file.getAbsolutePath());

        driver.switchTo().frame(0);
        System.out.println("frame(0)=" + driver.getCurrentUrl());

        driver.switchTo().defaultContent();

        driver.switchTo().frame(1);
        System.out.println("frame(1)=" + driver.getCurrentUrl());
    }

    @Test
    public void switchFrameUsingNameOrId() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver driver = new ChromeDriver();
        driver.get("file://" + file.getAbsolutePath());

        driver.switchTo().frame("Demo1");
        System.out.println("frame(\"Demo1\")=" + driver.getCurrentUrl());

        driver.switchTo().defaultContent();

        driver.switchTo().frame("Demo2");
        System.out.println("frame(\"Demo2\")=" + driver.getCurrentUrl());

        driver.switchTo().defaultContent();

        driver.switchTo().frame("iframe1");
        System.out.println("frame(\"iframe1\")=" + driver.getCurrentUrl());

        driver.switchTo().defaultContent();

        driver.switchTo().frame("iframe2");
        System.out.println("frame(\"iframe2\")=" + driver.getCurrentUrl());
    }


    @Test
    public void switchFrameUsingWebElement() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver driver = new ChromeDriver();
        driver.get("file://" + file.getAbsolutePath());

        WebElement element = driver.findElement(By.id("iframe1"));

        driver.switchTo().frame(element);
        System.out.println("frame(\"iframe1\")=" + driver.getCurrentUrl());
    }

    @Test
    public void switchBackToParentFrame() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver driver = new ChromeDriver();
        driver.get("file://" + file.getAbsolutePath());
        driver.switchTo().parentFrame();
        System.out.println(driver.getCurrentUrl());

        WebElement element = driver.findElement(By.id("iframe1"));

        driver.switchTo().frame(element);

        driver.switchTo().parentFrame();
        System.out.println(driver.getCurrentUrl());

        driver.switchTo().frame(1).switchTo().frame(0);
        driver.switchTo().parentFrame();
        System.out.println(driver.getCurrentUrl());


    }

    @Ignore
    @Test
    public void switchBackToDefaultContent() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver driver = new ChromeDriver();
        driver.get("file://" + file.getAbsolutePath());
        driver.switchTo().defaultContent();
        System.out.println(driver.getCurrentUrl());

        WebElement element = driver.findElement(By.id("iframe1"));

        driver.switchTo().frame(element);

        driver.switchTo().defaultContent();
        System.out.println(driver.getCurrentUrl());

        driver.switchTo().frame(1).switchTo().frame(0);
        driver.switchTo().defaultContent();
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void switchToActiceElement() {
        File file = new File("src/test/resources/html/iframeDemo.html");
        WebDriver driver = new ChromeDriver();
        driver.get("file://" + file.getAbsolutePath());
        driver.switchTo().frame(0);

        WebElement webElement = driver.switchTo().activeElement();
        String text = webElement.getText();
        System.out.println(text.substring(0, 100) + "... ...("+ (text.length() -100) +" characters omitted)");
    }

    @Test
    public void alert() throws InterruptedException {
        File file = new File("src/test/resources/html/alert.html");
        WebDriver driver = new ChromeDriver();
        driver.get("file://" + file.getAbsolutePath());
        driver.findElement(By.id("button1")).click();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert);
    }
}
