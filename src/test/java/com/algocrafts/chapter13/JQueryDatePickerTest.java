package com.algocrafts.chapter13;

import com.algocrafts.selenium.Browser;
import com.jquery.JQueryDatePickerPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.algocrafts.browsers.Browsers.*;
import static java.time.Month.APRIL;
import static java.util.EnumSet.of;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context.xml"})
public class JQueryDatePickerTest {

    @Autowired
    private JQueryDatePickerPage jQueryDatePickerPage;

    @Before
    public void setup() {
        jQueryDatePickerPage.open();
    }

    @Test
    public void pickADate() {
        jQueryDatePickerPage.pick(APRIL, 1, 2012);
        assertEquals("04/01/2012", jQueryDatePickerPage.getDate());
    }

    @Test
    public void testDifferentBrowsers() {
        for (Browser browser : of(CHROME, FIREFOX, SAFARI)) {
            jQueryDatePickerPage = new JQueryDatePickerPage(browser);
            jQueryDatePickerPage.open();
            jQueryDatePickerPage.pick(APRIL, 1, 2012);
            assertEquals("04/01/2012", jQueryDatePickerPage.getDate());
        }
    }

    @After
    public void close() {
        jQueryDatePickerPage.close();
    }
}
