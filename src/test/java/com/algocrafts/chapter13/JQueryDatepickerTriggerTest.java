package com.algocrafts.chapter13;

import com.algocrafts.browsers.Browsers;
import com.algocrafts.clickables.Url;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.Id;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Element;
import com.jquery.JQueryDatePickerPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.algocrafts.browsers.Browsers.*;
import static java.time.Month.APRIL;
import static java.util.EnumSet.of;
import static org.junit.Assert.assertEquals;


public class JQueryDatepickerTriggerTest {


    @Test
    public void popupCalendar1() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://jqueryui.com/datepicker");
        WebDriver frame = webDriver.switchTo().frame(0);
        WebElement element = frame.findElement(By.id("datepicker"));
        element.click();
    }

    @Test
    public void popupCalendar() {
        Browser chrome = Browsers.CHROME;
        chrome.get("http://try.sencha.com/extjs/4.0.7/docs/Ext.menu.DatePicker.1/");
        Page page = new Page(chrome);
        Page frame = page.frame(1);
        System.out.println(frame.getTitle());
        System.out.println(chrome.getCurrentUrl());
        Element element = frame.untilFound(() -> By.id("menuitem-1017"));
        frame.mouseOver(element);
    }

}
