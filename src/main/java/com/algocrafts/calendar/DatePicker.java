package com.algocrafts.calendar;


/**
 * A general purpose DatePicker can be used to pick a given date from
 * the calendar flyout provided by JavaScript framework.
 *
 * @author Yujun Liang
 * @since 0.1
 */
public class Datepicker {

    private final Calendar calendar;

    /**
     * Constructor of the DatePicker which taking a Calendar interface.
     *
     * @param calendar calendar
     */
    public Datepicker(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Pick a date by the given parameter.
     * for example,
     * datePicker.pick(Month.JULY, 31, 1999)
     *
     * @param month it need to be defined as an enum to make the code cleaner.
     * @param day   an integer representing the day appearing on the calendar
     * @param year  an ineger representing the year appearing on the calendar
     */
    public void pick(Enum month, int day, int year) {
        calendar.show();
        calendar.pickYear(year);
        calendar.pickMonth(month);
        calendar.pickDay(day);
    }

}