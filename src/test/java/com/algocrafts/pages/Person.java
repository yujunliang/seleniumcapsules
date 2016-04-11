package com.algocrafts.pages;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class Person {
    private int number;
    private final String firstName;
    private final String lastName;
    private final int points;

    public Person(int number, String firstName, String lastName, int points) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
    }

    @Override
    public String toString() {
        return "new Person(\"" + number +
                "\",\"" + firstName + "\",\"" +
                lastName + "\"," + points + ")\n";
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}