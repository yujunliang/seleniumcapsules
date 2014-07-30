package com.algocrafts.calendar;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

public interface DayLocatorFactory {

    Locator<Page, Void> forDay(int day);
}
