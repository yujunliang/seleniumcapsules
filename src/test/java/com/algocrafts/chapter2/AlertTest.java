package com.algocrafts.chapter2;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * Created by yujunliang on 8/31/14.
 */
public class AlertTest {

@Test
public void dismiss()  {
    File file = new File("src/test/resources/html/confirm.html");
    WebDriver driver = new ChromeDriver();
    driver.get("file://" + file.getAbsolutePath());
    driver.findElement(By.id("button2")).click();

    Alert alert = driver.switchTo().alert();
    alert.dismiss();
}

@Test
public void accept()  {
    File file = new File("src/test/resources/html/alert.html");
    WebDriver driver = new ChromeDriver();
    driver.get("file://" + file.getAbsolutePath());
    driver.findElement(By.id("button1")).click();

    Alert alert = driver.switchTo().alert();
    alert.accept();
}

@Test
public void accept2()  {
    File file = new File("src/test/resources/html/confirm.html");
    WebDriver driver = new ChromeDriver();
    driver.get("file://" + file.getAbsolutePath());
    driver.findElement(By.id("button2")).click();

    Alert alert = driver.switchTo().alert();
    alert.accept();
}

@Test
public void getText()  {
    File file = new File("src/test/resources/html/confirm.html");
    WebDriver driver = new ChromeDriver();
    driver.get("file://" + file.getAbsolutePath());
    driver.findElement(By.id("button2")).click();

    Alert alert = driver.switchTo().alert();
    System.out.println(alert.getText());
}

@Test
public void sendKeys()  {
    File file = new File("src/test/resources/html/prompt.html");
    WebDriver driver = new ChromeDriver();
    driver.get("file://" + file.getAbsolutePath());
    driver.findElement(By.id("button2")).click();

    Alert alert = driver.switchTo().alert();
    alert.sendKeys("35");
    alert.accept();
}

}
