package com.algocrafts;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.Xpath;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.table.Table;
import com.algocrafts.table.TableContents;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.TagName.TABLE;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class TableTest {

    private TableContents<Person> expected = new TableContents<>(
            newHashSet("Number", "First Name", "Last Name", "Points"),
            Sets.<Person>newHashSet(
                    new Person(4, "Jill", "Smith", 50)
                    , new Person(3, "Adam", "Johnson", 67)
                    , new Person(2, "John", "Doe", 80)
                    , new Person(1, "Eve", "Jackson", 94)
            )
    );

    @Test
    public void testReadFromTable() {

        Browsers browser = Browsers.FIREFOX;
        browser.get("http://www.w3schools.com/html/html_tables.asp");
        Page page = new Page(browser);
        Locator<Page, Element> locator = Locators.<Page>element(Xpath.TABLE_CONTAINER).andThen(element(TABLE));
        Locator<Stream<Element>, Person> mapper = (stream) -> {
            Iterator<String> iterator = stream.map(TEXT).iterator();
            return new Person(PARSE_INT.locate(iterator.next()), iterator.next(), iterator.next(), PARSE_INT.locate(iterator.next()));
        };
        Table<Person, Page> table = new Table<>(page, locator, mapper);

        assertTrue(expected, expected.equals(table.getContents()));
        browser.close();
    }

    private void assertTrue(Object diff, boolean pass) {
        if (!pass) {
            Assert.assertTrue(diff.toString(), pass);
        }
    }

    class Person {
        private int number;
        private final String firstName;
        private final String lastName;
        private final int points;

        Person(int number, String firstName, String lastName, int points) {
            this.number = number;
            this.firstName = firstName;
            this.lastName = lastName;
            this.points = points;
        }

        @Override
        public String toString() {
            return "new Person(\"" + number + "\",\"" + firstName + "\",\"" + lastName + "\"," + points + ")\n";
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

}
