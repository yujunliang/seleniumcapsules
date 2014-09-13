package com.algocrafts;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.google.GooglePage;
import com.google.common.base.Function;
import com.orgsync.OrgSyncHomePage;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.Name.Q;
import static com.algocrafts.selectors.TagName.SPAN;
import static com.algocrafts.selectors.VariableXpath.GOOGLE_AUTOCOMPLETE;
import static org.openqa.selenium.By.className;
import static org.slf4j.LoggerFactory.getLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context.xml"})
public class OrgSyncHomePageTest {

    private static final Logger logger = getLogger(OrgSyncHomePageTest.class);

    @Autowired
    private OrgSyncHomePage homePage;


    @Before
    public void setup() {
        homePage.open();
    }

    @Test
    public void start() {
        homePage.getAllMenu().forEach(menu -> {
            try {
                menu.click();
                String title = homePage.until((Page input) -> homePage.getTitle());
                logger.info("clicking " + menu + " and title is \"" + title + "\"");
            } catch (Exception e) {
                logger.info("Error clicking " + menu, e);
            }
        });
    }

    @After
    public void close() {
        homePage.close();
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = {"classpath:context/context.xml"})
    public static class GoogleAutoCompleteTest {

        private static final Logger log = getLogger(GoogleAutoCompleteTest.class);
        @Autowired
        GooglePage googlePage;

        @Before
        public void setup() {
            googlePage.open();
        }



        //This is an ugly test not using page framework, it has the same function as the test below. :(
        @Test
        public void autoCompeleteUsingSelenium() throws InterruptedException {
            WebDriver webDriver = Browsers.CHROME;
            webDriver.get("http://google.com");
            WebElement q = webDriver.findElement(By.name("q"));

            q.clear();
            WebElement suggest = null;
            for (char c : "ticketfly".toCharArray()) {
                q.sendKeys(String.valueOf(c));
                try {
                    suggest = webDriver.findElement(
                            By.xpath("//table[contains(@class, 'gssb_c')]/descendant::span[text()='ticketfly']"));
                    suggest.click();
                } catch (NoSuchElementException e) {
                    log.debug("This is OK", e);
                }
            }
            if (suggest == null) {
                suggest = new WebDriverWait(webDriver, 1).until(new Function<WebDriver, WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        return webDriver.findElement(
                                By.xpath("//table[contains(@class, 'gssb_c')]/descendant::span[text()='ticketfly']"));
                    }
                });
                suggest.click();
            }
        }

        /**
         * This is a clean test using page framework.  it has the same function as the test above.  :)
         */
        @Test
        public void autoCompleteUsingSeleniumCapsules() {
            googlePage.autocomplete(Q, "manning", GOOGLE_AUTOCOMPLETE);
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
            googlePage.autocomplete(Q, "oracle",
                    Locators.<Page>optionalElement(() -> className("gssb_c"))
                            .andThen(GET)
                            .andThen(elements(SPAN))
                            .andThen(new FirstMatch<>(TEXT.and(new Equals("ticketfly")))));
        }

        @After
        public void close() {
            googlePage.close();
        }
    }
}
