package com.algocrafts.chapter2;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.WebDriver.Navigation;

/**
 * Created by yujunliang on 8/31/14.
 */
public class NavigationTest {

    @Test
    public void navagate() throws InterruptedException, MalformedURLException {
        WebDriver driver = new ChromeDriver();

        driver.get("http://ticketfly.com");
        driver.get("http://jqueryui.com");

        Navigation navigate = driver.navigate();
        navigate.back();
        System.out.println("navigate.back()=" + driver.getCurrentUrl());

        navigate.forward();
        System.out.println("navigate.forward()=" + driver.getCurrentUrl());

        navigate.to("http://ticketfly.com/events");
        System.out.println("navigate.to(\"http://ticketfly.com/events\")="
                + driver.getCurrentUrl());

        navigate.to(new URL("http://jqueryui.com/datepicker"));
        System.out.println("navigate.to(new URL(\"http://jqueryui.com/datepicker\"))="
                + driver.getCurrentUrl());

        navigate.refresh();
        System.out.println("navigate.refresh()="
                + driver.getCurrentUrl());

    }

}
