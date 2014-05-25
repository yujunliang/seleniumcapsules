package com.algocrafts;

import com.jquery.JQueryDatePickerPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context.xml"})
public class JQueryDatePickerTest {

    @Autowired
    JQueryDatePickerPage jQueryDatePickerPage;

    @Before
    public void setup() {
        jQueryDatePickerPage.open();
    }

    @Test
    public void pickADate() {
        jQueryDatePickerPage.pick(APRIL, 1, 2012);
        assertEquals("04/01/2012", jQueryDatePickerPage.getDate());
    }

    @After
    public void close() {
        jQueryDatePickerPage.close();
    }
}
