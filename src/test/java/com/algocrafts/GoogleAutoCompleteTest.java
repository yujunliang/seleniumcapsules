package com.algocrafts;

import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.google.GooglePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.Name.Q;
import static com.algocrafts.selectors.TagName.DIV;
import static com.algocrafts.selectors.Xpath.GOOGLE_AUTOCOMPLETE;
import static org.openqa.selenium.By.id;

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
        googlePage.autocomplete(Q, "ticketfly", elements(GOOGLE_AUTOCOMPLETE));
    }

    /**
     * This is a cleaner test using page framework.  it has the same function as the test above.  :)
     */
    @Test
    public void autoCompleteUsingSeleniumCapsules1() {
        googlePage.autocomplete("ticketfly");
    }


    @Test
    public void autoCompleteUsingLocator() {
        googlePage.autocomplete(Q, "ticketfly",
                Locators.<Page>element(() -> id("sbse0"))
                        .andThen(elements(DIV)));
    }

    @After
    public void close() {
        googlePage.close();
    }
}
