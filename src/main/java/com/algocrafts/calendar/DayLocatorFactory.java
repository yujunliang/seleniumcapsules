package com.algocrafts.calendar;

import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Locator;

public interface DayLocatorFactory {

    Locator<AbstractPage, Void> forDay(int day);
}
