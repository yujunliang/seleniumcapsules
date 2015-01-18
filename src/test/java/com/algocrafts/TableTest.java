package com.algocrafts;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.table.Table;
import com.algocrafts.table.TableContents;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.Id.MAIN;
import static com.algocrafts.selectors.TagName.TABLE;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class TableTest {

    private TableContents<Person> expected = new TableContents<>(
            newHashSet("Firstname", "Lastname", "Points"),
            Sets.<Person>newHashSet(
                    new Person("Jill", "Smith", 50)
                    , new Person("Adam", "Johnson", 67)
                    , new Person("John", "Doe", 80)
                    , new Person("Eve", "Jackson", 94)
            )
    );

    @Test
    public void testReadFromTable() {

        Browsers chrome = CHROME;
        chrome.get("http://www.w3schools.com/html/html_tables.asp");
        Page page = new Page(chrome);
        Locator<Page, Element> locator = Locators.<Page>element(MAIN).andthen(element(TABLE));
        Locator<Stream<Element>, Person> mapper = (stream) -> {
            Iterator<String> iterator = stream.map(TEXT).iterator();
            return new Person(iterator.next(), iterator.next(), PARSE_INT.locate(iterator.next()));
        };
        Table<Person, Page> table = new Table<>(page, locator, mapper);

        assertTrue(expected, expected.equals(table.getContents()));
    }

    private void assertTrue(Object diff, boolean pass) {
        Assert.assertTrue(diff.toString(), pass);
    }

    class Person {
        private final String firstName;
        private final String lastName;
        private final int points;

        Person(String firstName, String lastName, int points) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.points = points;
        }

        @Override
        public String toString() {
            return "new Person(\"" + firstName + "\",\"" + lastName + "\"," + points + ")\n";
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
