package com.algocrafts.chapter2;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.openqa.selenium.WebDriver.Options;
import static org.openqa.selenium.WebDriver.Window;

@Ignore
public class CookieTest {

    @Test
    public void cookies() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://ticketfly.com");

        Options manage = driver.manage();
        manage.getCookies().stream().forEach(System.out::println);
        manage.deleteAllCookies();
        System.out.println("After manage.deleteAllCookies();");
        manage.getCookies().stream().forEach(System.out::println);
        System.out.println("After adding two cookies");

        manage.addCookie(new Cookie("Test", "just for test"));
        manage.addCookie(new Cookie("Test2", "just for test too"));
        manage.getCookies().stream().forEach(System.out::println);

        Cookie test = manage.getCookieNamed("Test");
        System.out.println("Test Cookie is:" + test);
        manage.deleteCookie(test);

        manage.deleteCookieNamed("Test2");
        System.out.println("After deleting two cookies");
        manage.getCookies().stream().forEach(System.out::println);
        System.out.println("Done");
    }
}
