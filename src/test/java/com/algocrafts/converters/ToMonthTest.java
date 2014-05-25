package com.algocrafts.converters;

import org.junit.Test;

import static com.algocrafts.converters.StringToMonth.TO_MONTH;
import static java.time.Month.APRIL;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertEquals;

public class ToMonthTest {

    @Test
    public void test() {
        assertEquals(APRIL, TO_MONTH.apply("apr"));
        assertEquals(APRIL, TO_MONTH.apply("apr."));
        assertEquals(APRIL, TO_MONTH.apply("Apr."));
        assertEquals(APRIL, TO_MONTH.apply("April"));
        assertEquals(JANUARY, TO_MONTH.apply("Jan."));
    }

}