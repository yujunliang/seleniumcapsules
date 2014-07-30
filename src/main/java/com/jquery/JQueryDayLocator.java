package com.jquery;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.conditions.PagePredicates.CALENDAR_NOT_DISPLAYED;
import static com.jquery.CalendarVoidLocator.CLOSE_BUTTON;

public class JQueryDayLocator implements Locator<Page, Void>  {

    public JQueryDayLocator(Locator<Page, Void> locator) {
        this.locator = locator;
    }

    private Locator<Page, Void> locator;

    @Override
    public Void locate(Page page) {
        locator.locate(page);
        CLOSE_BUTTON.locate(page);
        page.until(CALENDAR_NOT_DISPLAYED);
        return null;
    }
}
