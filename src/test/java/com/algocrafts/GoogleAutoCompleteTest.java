package com.algocrafts;

import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.google.GooglePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.locators.Locators.optionalElement;
import static com.algocrafts.selectors.Name.Q;
import static com.algocrafts.selectors.TagName.SPAN;
import static com.algocrafts.selectors.Xpath.TICKETFLY_AUTOCOMPLETE;
import static org.openqa.selenium.By.className;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context.xml"})
public class GoogleAutoCompleteTest {

    @Autowired
    GooglePage googlePage;

    @Before
    public void setup() {
        googlePage.open();
    }


    /**
     * This is a clean test using page framework.  it has the same function as the test above.  :)
     */
    @Test
    public void autoCompleteUsingSeleniumCapsules() {
        googlePage.autocomplete(Q, "ticketfly", optionalElement(TICKETFLY_AUTOCOMPLETE));
    }

    /**
     * This is a cleaner test using page framework.  it has the same function as the test above.  :)
     */
    @Test
    public void autoCompleteUsingSeleniumCapsules1() {
        WebDriver webdriver = new FirefoxDriver();
        webdriver.get("http://www.google.com");
    }


    @Test
    public void autoCompleteUsingLocator() {
        googlePage.autocomplete(Q, "ticketfly",
                Locators.<Page>optionalElement(() -> className("gssb_c"))
                        .andthen(GET)
                        .andthen(elements(SPAN))
                        .andthen(new FirstMatch<>(TEXT.and(new Equals("ticketfly")))));
    }

    @After
    public void close() {
        googlePage.close();
    }
}
