package com.algocrafts.chapter13;

import com.algocrafts.selenium.Browser;
import com.jquery.datepicker.JQueryDatePickerPage;
import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.algocrafts.browsers.Browsers.*;
import static java.time.Month.APRIL;
import static java.util.EnumSet.of;
import static org.junit.Assert.assertEquals;

@Ignore
public class JQueryDatePickerMultipleBrowserTest {

    private StopWatch stopWatch = new StopWatch();

    @Before
    public void startStopWatch() {
        stopWatch.start();
    }

    @After
    public void print() {
        System.out.println("Taken " + stopWatch);
    }

    @Test
    public void testDifferentBrowsers() {
        for (Browser browser : of(CHROME, FIREFOX, SAFARI)) {
            JQueryDatePickerPage jQueryDatePickerPage = new JQueryDatePickerPage(browser);
            jQueryDatePickerPage.open();
            jQueryDatePickerPage.pick(APRIL, 1, 2012);
            assertEquals("04/01/2012", jQueryDatePickerPage.getDate());
            browser.quit();
        }
    }

}
