package com.jquery.datepicker.locators;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.By;

public class NextMonthButtonClicker implements Locator<Page, Void> {

    @Override
    public Void locate(Page page) {
        page.untilFound(() -> By.id("ui-datepicker-div"))
                .untilFound(() -> By.className("ui-datepicker-next"))
                .click();
        return null;
    }
}
