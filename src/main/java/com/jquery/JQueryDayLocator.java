package com.jquery;

import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.conditions.PagePredicates.CALENDAR_NOT_DISPLAYED;
import static com.jquery.CalendarVoidLocator.CLOSE_BUTTON;

public class JQueryDayLocator implements Locator<AbstractPage, Void>  {

    public JQueryDayLocator(Locator<AbstractPage, Void> locator) {
        this.locator = locator;
    }

    private Locator<AbstractPage, Void> locator;

    @Override
    public Void locate(AbstractPage page) {
        locator.locate(page);
        CLOSE_BUTTON.locate(page);
        page.until(CALENDAR_NOT_DISPLAYED);
        return null;
    }
}
