package com.algocrafts.chapter13;

import com.extjs.ExtJSDatePickerPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.algocrafts.browsers.Browsers.CHROME;
import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;


public class ExtJSDatePickerTest {

    private ExtJSDatePickerPage extJSDatePickerPage = new ExtJSDatePickerPage(CHROME);

    @Before
    public void setup() {
        extJSDatePickerPage.open();
    }

    @Test
    public void pickADate() {
        extJSDatePickerPage.pick(APRIL, 1, 2012);
        assertEquals("You selected Apr 1, 2012", extJSDatePickerPage.getDate());
    }

    @After
    public void close() {
        extJSDatePickerPage.close();
    }
}
