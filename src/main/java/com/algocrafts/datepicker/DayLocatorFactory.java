package com.algocrafts.datepicker;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

public interface DayLocatorFactory {

    Locator<Page, Void> forDay(int day);
}
