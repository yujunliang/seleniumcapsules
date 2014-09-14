package com.algocrafts.chapter13;

import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class NaiveJQueryDatepickerTest {

    private StopWatch stopWatch = new StopWatch();
    @Before
    public void startStopWatch() {
        stopWatch.start();
    }

    @After
    public void print() {
        System.out.println("Taken " + stopWatch);
    }

    Date date;

    @Before
    public void date() throws ParseException {
        date = new SimpleDateFormat("MM/dd/yyyy").parse("04/01/2012");
    }

    @Test
    public void pickDateUsingNaivaDatepicker() {
        assertEquals("04/01/2012", new NaivaDatepicker().pickDate(date));
    }

    @Test
    public void pickDateUsingBetterJQueryDatepicker() {
        assertEquals("04/01/2012", new BetterJQueryDatepicker().pickDate(date));
    }

}
