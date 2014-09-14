package com.jquery.locators;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.By;

public class TriggerClicker implements Locator<Page, Void> {

    @Override
    public Void locate(Page page) {
        page.frame(0)
                .untilFound(() -> By.id("datepicker"))
                .click();
        return null;
    }
}
