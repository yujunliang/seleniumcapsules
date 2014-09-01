package com.algocrafts.chapter2;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.WebDriver.Options;
import static org.openqa.selenium.WebDriver.Window;

/**
 * Created by yujunliang on 8/31/14.
 */
public class WindowsTest {

    @Test
    public void windowsOperations() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://ticketfly.com");

        Options manage = webDriver.manage();
        Window window = manage.window();
        System.out.println("window.getPosition()=" + window.getPosition());
        System.out.println("window.getSize()=" + window.getSize());

        window.maximize();
        System.out.println(" window.maximize() - first call");
        System.out.println("window.getPosition()=" + window.getPosition());
        System.out.println("window.getSize()=" + window.getSize());

        window.maximize();
        System.out.println(" window.maximize() - second call");
        System.out.println("window.getPosition()=" + window.getPosition());
        System.out.println("window.getSize()=" + window.getSize());

        window.setPosition(new Point(200,400));
        window.setSize(new Dimension(400, 800));

        System.out.println("after setPosition and setSize");
        System.out.println("window.getPosition()=" + window.getPosition());
        System.out.println("window.getSize()=" + window.getSize());
    }
}
