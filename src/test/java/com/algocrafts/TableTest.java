package com.algocrafts;


import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Browsers;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locators;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Locator;
import com.algocrafts.table.Table;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.pages.Locators.element;
import static com.algocrafts.selectors.Id.MAIN;
import static com.algocrafts.selectors.TagName.TABLE;
import static java.util.stream.Collectors.toList;

public class TableTest {

    @Test
    public void testDragAndDrop() {
        Browser browser = Browsers.FIREFOX;
        browser.get("http://www.w3schools.com/html/html_tables.asp");
        AbstractPage page = new AbstractPage(browser);
        Locator<AbstractPage, Element> locator = Locators.<AbstractPage>element(MAIN).and(element(TABLE));
        Locator<Stream<Element>, Person> mapper = (stream) -> {
            List<String> collect = stream.map(TEXT).collect(toList());
            return new Person(collect.get(0), collect.get(1), collect.get(2));
        };
        Table<Person> table = new Table<>(page, locator, mapper);
        table.getHeader().forEach(e -> System.out.print(e + "|"));
        System.out.println();
        table.getRows().forEach(
                person -> System.out.println(person)
        );
    }

    class Person {
        private final String firstName;
        private final String lastName;
        private final String points;

        Person(String firstName, String lastName, String points) {

            this.firstName = firstName;
            this.lastName = lastName;
            this.points = points;
        }

        @Override
        public String toString() {
            return firstName + "|" + lastName + "|" + points;
        }
    }

}
