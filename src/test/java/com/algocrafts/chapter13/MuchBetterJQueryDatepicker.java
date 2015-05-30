package com.algocrafts.chapter13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Month;
import java.util.Date;
import java.util.List;


public class MuchBetterJQueryDatepicker {

    private WebDriver frame;
    private WebDriver driver;


    public String pickDate(Date date) {

        driver = new ChromeDriver();
        driver.get("http://jqueryui.com/datepicker");

        show();
        pickYear(date.getYear() + 1900);
        pickMonth(date.getMonth());
        pickDay(date.getDay() + 1);

        return  frame.findElement(By.id("datepicker")).getAttribute("value");
    }

    private void show() {
        frame = driver.switchTo().frame(0);
        WebElement element = frame.findElement(By.id("datepicker"));
        element.click();
    }

    private void pickYear(int year) {
        if (displayedYear() < year) {
            while (displayedYear() != year) {
                nextMonth();
            }
        } else if (displayedYear() > year) {
            while (displayedYear() != year) {
                previousMonth();
            }
        }
    }

    private void pickMonth(int month) {
        if (displayedMonth() < month) {
            while (displayedMonth() != month) {
                nextMonth();
            }
        } else if (displayedMonth() > month) {
            while (displayedMonth() != month) {
                previousMonth();
            }
        }
    }

    private void pickDay(int day) {
        List<WebElement> tds = calendar().findElements(By.tagName("td"));
        for (WebElement td : tds) {
            if (td.getText().equals(String.valueOf(day))) {
                td.click();
            }
        }
    }

    private void previousMonth() {
        calendar().findElement(By.className("ui-datepicker-prev")).click();
    }

    private void nextMonth() {
        calendar().findElement(By.className("ui-datepicker-next")).click();
    }

    private WebElement calendar() {
        return frame.findElement(By.id("ui-datepicker-div"));
    }

    private int displayedYear() {
        return Integer.parseInt(calendar().findElement(By.className("ui-datepicker-year")).getText());
    }


    private int displayedMonth() {
        return Month.valueOf(calendar().findElement(By.className("ui-datepicker-month")).getText().toUpperCase()).ordinal();
    }

}
