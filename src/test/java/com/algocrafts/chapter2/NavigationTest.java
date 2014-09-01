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
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("http://ticketfly.com");
        webDriver.get("http://jqueryui.com");

        Navigation navigate = webDriver.navigate();
        navigate.back();
        System.out.println("navigate.back()=" + webDriver.getCurrentUrl());

        navigate.forward();
        System.out.println("navigate.forward()=" + webDriver.getCurrentUrl());

        navigate.to("http://ticketfly.com/events");
        System.out.println("navigate.to(\"http://ticketfly.com/events\")="
                + webDriver.getCurrentUrl());

        navigate.to(new URL("http://jqueryui.com/datepicker"));
        System.out.println("navigate.to(new URL(\"http://jqueryui.com/datepicker\"))="
                + webDriver.getCurrentUrl());

        navigate.refresh();
        System.out.println("navigate.refresh()="
                + webDriver.getCurrentUrl());

    }

}
