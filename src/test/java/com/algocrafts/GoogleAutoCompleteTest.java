package com.algocrafts;

import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.pages.Locators;
import com.algocrafts.pages.AbstractPage;
import com.google.GooglePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.pages.Locators.elements;
import static com.algocrafts.pages.Locators.trying;
import static com.algocrafts.selectors.Name.Q;
import static com.algocrafts.selectors.TagName.SPAN;
import static com.algocrafts.selectors.Xpath.ORACLE_AUTOCOMPLETE;
import static org.openqa.selenium.By.className;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context.xml"})
public class GoogleAutoCompleteTest {

    private static final Logger log = LoggerFactory.getLogger(GoogleAutoCompleteTest.class);
    @Autowired
    GooglePage googlePage;

    @Before
    public void setup() {
        googlePage.open();
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void autoCompeleteUsingSelenium() throws InterruptedException {
        FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/Contents/MacOS/firefox-bin"));
        FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
        WebDriver webDriver = new FirefoxDriver(binary, profile);
        webDriver.get("http://google.com");
        WebElement q = webDriver.findElement(By.name("q"));
        q.clear();
        for (char c : "oracle".toCharArray()) {
            q.sendKeys(String.valueOf(c));
            try {
                Thread.sleep(50);
                WebElement oracle = webDriver.findElement( ORACLE_AUTOCOMPLETE.get());
                oracle.click();
            } catch (NoSuchElementException e) {
                log.debug("This is OK", e);
            }
        }
    }

    /**
     * This is a clean test using page framework.  it has the same function as the test above.  :)
     */
    @Test
    public void autoCompleteUsingXpath() {
        googlePage.autocomplete(Q, "oracle", trying(ORACLE_AUTOCOMPLETE));
    }

    @Test
    public void autoCompleteUsingLocator() {
        googlePage.autocomplete(Q, "oracle",
                Locators.<AbstractPage>trying(() -> className("gssb_c"))
                        .and(elements(SPAN))
                        .and(new FirstMatch<>(TEXT.and(new IsStringEqual("oracle")))));
    }

    @After
    public void close() {
        googlePage.close();
    }
}
