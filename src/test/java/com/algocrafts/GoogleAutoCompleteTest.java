package com.algocrafts;

import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.converters.Filter;
import com.algocrafts.converters.FirstItem;
import com.algocrafts.converters.GetText;
import com.algocrafts.locators.ElementTryLocator;
import com.algocrafts.locators.ElementsLocator;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.searchmethods.TagName;
import com.google.GooglePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.algocrafts.searchmethods.Name.Q;
import static com.algocrafts.searchmethods.Xpath.ORACLE_AUTOCOMPLETE;
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

    @Test
    public void autoCompleteUsingXpath() {
        googlePage.autocomplete(Q, "oracle", new ElementTryLocator<>(ORACLE_AUTOCOMPLETE));
    }


    @Test
    public void autoCompleteUsingLocator() {
        googlePage.autocomplete(Q, "oracle",
                new ElementTryLocator<AbstractPage>(() -> className("gssb_c"))
                        .and(new ElementsLocator<>(TagName.SPAN))
                        .and(new Filter<>(GetText.TEXT.and(new IsStringEqual("oracle"))))
                        .and(new FirstItem<>()));
    }

    @After
    public void close() {
        googlePage.close();
    }
}
