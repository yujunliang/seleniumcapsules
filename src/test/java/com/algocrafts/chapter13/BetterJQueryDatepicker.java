package com.algocrafts.chapter13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Month;
import java.util.Date;
import java.util.List;


public class BetterJQueryDatepicker {

    private WebDriver frame;
    private WebDriver webDriver;
    private WebElement datepicker;


    public String pickDate(Date date) {

        webDriver = new ChromeDriver();
        webDriver.get("http://jqueryui.com/datepicker");

        show();
        pickYear(date.getYear() + 1900);
        pickMonth(date.getMonth());
        pickDay(date.getDay() + 1);

        return  frame.findElement(By.id("datepicker")).getAttribute("value");
    }

    private void show() {
        frame = webDriver.switchTo().frame(0);
        WebElement element = frame.findElement(By.id("datepicker"));
        element.click();
    }

    private void pickYear(int yearInt) {
        datepicker = frame.findElement(By.id("ui-datepicker-div"));
        String year = datepicker.findElement(By.className("ui-datepicker-year")).getText();

        if (Integer.parseInt(year) < yearInt) {
            while (Integer.parseInt(year) !=  yearInt) {
                datepicker.findElement(By.className("ui-datepicker-next")).click();
                datepicker = frame.findElement(By.id("datepicker"));
                year = datepicker.findElement(By.className("ui-datepicker-year")).getText();
            }
        } else if (Integer.parseInt(year) > yearInt) {
            while (Integer.parseInt(year) != yearInt) {
                datepicker.findElement(By.className("ui-datepicker-prev")).click();
                datepicker = frame.findElement(By.id("ui-datepicker-div"));
                year = datepicker.findElement(By.className("ui-datepicker-year")).getText();
            }
        }
    }

    private void pickMonth(int month1) {
        String month = datepicker.findElement(By.className("ui-datepicker-month")).getText();
        if (Month.valueOf(month.toUpperCase()).ordinal() < month1) {
            while (Month.valueOf(month.toUpperCase()).ordinal() != month1) {
                datepicker.findElement(By.className("ui-datepicker-next")).click();
                datepicker = frame.findElement(By.id("ui-datepicker-div"));
                month = datepicker.findElement(By.className("ui-datepicker-month")).getText();
            }
        } else if (Month.valueOf(month.toUpperCase()).ordinal() > month1) {
            while (Month.valueOf(month.toUpperCase()).ordinal() != month1) {
                datepicker.findElement(By.className("ui-datepicker-prev")).click();
                datepicker = frame.findElement(By.id("ui-datepicker-div"));
                month = datepicker.findElement(By.className("ui-datepicker-month")).getText();
            }
        }
    }

    private void pickDay(int day) {
        List<WebElement> tds = datepicker.findElements(By.tagName("td"));
        for (WebElement td : tds) {
            if (td.getText().equals(String.valueOf(day))) {
                td.click();
            }
        }
    }
}
