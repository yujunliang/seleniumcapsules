package com.algocrafts;

import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.locators.ElementTryLocator;
import com.algocrafts.locators.ElementsLocator;
import com.algocrafts.converters.FirstMatch;
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
    public void autoCompeleteUsingSelenium() {
        FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/Contents/MacOS/firefox-bin"));
        FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
        WebDriver webDriver = new FirefoxDriver(binary, profile);
        webDriver.get("http://google.com");
        WebElement q = webDriver.findElement(By.name("q"));
        q.clear();
        for (char c : "oracle".toCharArray()) {
            q.sendKeys(String.valueOf(c));
            try {
                WebElement oracle = webDriver.findElement(
                        By.xpath("//table[contains(concat(' ', @class, ' '), 'gssb_c')]/descendant::span[text()='oracle']"));
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
        googlePage.autocomplete(Q, "oracle", new ElementTryLocator<>(ORACLE_AUTOCOMPLETE));
    }

    @Test
    public void autoCompleteUsingLocator() {
        googlePage.autocomplete(Q, "oracle",
                new ElementTryLocator<AbstractPage>(() -> className("gssb_c"))
                        .and(new ElementsLocator<>(SPAN))
                        .and(new FirstMatch<>(TEXT.and(new IsStringEqual("oracle")))));
    }

    @After
    public void close() {
        googlePage.close();
    }
}
