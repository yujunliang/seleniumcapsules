package com.algocrafts;

import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.AbstractPage;
import com.google.GooglePage;
import com.google.common.base.Function;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.locators.Locators.optional;
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
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://google.com");
        WebElement q = webDriver.findElement(By.name("q"));

        q.clear();
        WebElement oracle = null;
        for (char c : "oracle".toCharArray()) {
            q.sendKeys(String.valueOf(c));
            try {
                oracle = webDriver.findElement(
                        By.xpath("//table[contains(@class, 'gssb_c')]/descendant::span[text()='oracle']"));
                oracle.click();
            } catch (NoSuchElementException e) {
                log.debug("This is OK", e);
            }
        }
        if (oracle == null) {
            oracle = new WebDriverWait(webDriver, 1).until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver webDriver) {
                    return webDriver.findElement(
                            By.xpath("//table[contains(@class, 'gssb_c')]/descendant::span[text()='oracle']"));
                }
            });
            oracle.click();
        }
    }

    /**
     * This is a clean test using page framework.  it has the same function as the test above.  :)
     */
    @Test
    public void autoCompleteUsingSeleniumCapsules() {
        googlePage.autocomplete(Q, "oracle", optional(ORACLE_AUTOCOMPLETE));
    }

    @Test
    public void autoCompleteUsingLocator() {
        googlePage.autocomplete(Q, "oracle",
                Locators.<AbstractPage>optional(() -> className("gssb_c"))
                        .and(GET)
                        .and(elements(SPAN))
                        .and(new FirstMatch<>(TEXT.and(new Equals("oracle")))));
    }

    @After
    public void close() {
        googlePage.close();
    }
}
