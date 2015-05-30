package com.algocrafts.chapter13;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Month;
import java.util.Date;
import java.util.List;

public class NaiveDatepicker {

    public String pickDate(Date date) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://jqueryui.com/datepicker");
        WebDriver frame = driver.switchTo().frame(0);
        WebElement element = frame.findElement(By.id("datepicker"));
        element.click();
        WebElement datepicker = frame.findElement(By.id("ui-datepicker-div"));
        String year = datepicker.findElement(By.className("ui-datepicker-year")).getText();
        if (Integer.parseInt(year) < date.getYear() + 1900) {
            while (Integer.parseInt(year) !=  date.getYear() + 1900) {
                datepicker.findElement(By.className("ui-datepicker-next")).click();
                datepicker = frame.findElement(By.id("datepicker"));
                year = datepicker.findElement(By.className("ui-datepicker-year")).getText();
            }
        } else if (Integer.parseInt(year) > date.getYear()) {
            while (Integer.parseInt(year) != date.getYear() + 1900) {
                datepicker.findElement(By.className("ui-datepicker-prev")).click();
                datepicker = frame.findElement(By.id("ui-datepicker-div"));
                year = datepicker.findElement(By.className("ui-datepicker-year")).getText();
            }
        }
        String month = datepicker.findElement(By.className("ui-datepicker-month")).getText();
        if (Month.valueOf(month.toUpperCase()).ordinal() < date.getMonth()) {
            while (Month.valueOf(month.toUpperCase()).ordinal() != date.getMonth()) {
                datepicker.findElement(By.className("ui-datepicker-next")).click();
                datepicker = frame.findElement(By.id("ui-datepicker-div"));
                month = datepicker.findElement(By.className("ui-datepicker-month")).getText();
            }
        } else if (Month.valueOf(month.toUpperCase()).ordinal() > date.getMonth()) {
            while (Month.valueOf(month.toUpperCase()).ordinal() != date.getMonth()) {
                datepicker.findElement(By.className("ui-datepicker-prev")).click();
                datepicker = frame.findElement(By.id("ui-datepicker-div"));
                month = datepicker.findElement(By.className("ui-datepicker-month")).getText();
            }
        }
        List<WebElement> tds = datepicker.findElements(By.tagName("td"));
        for (WebElement td : tds) {
            if (td.getText().equals(String.valueOf(date.getDay() + 1))) {
                td.click();
            }
        }
        return frame.findElement(By.id("datepicker")).getAttribute("value");
    }

}
