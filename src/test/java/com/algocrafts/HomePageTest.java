package com.algocrafts;


import com.algocrafts.clickables.Url;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Browser;
import com.orgsync.AllMenuLocatorForOrgSync;
import com.orgsync.OrgSyncHomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.EnumSet;

import static com.algocrafts.pages.Browsers.CHROME;
import static com.algocrafts.pages.Browsers.FIREFOX;
import static org.slf4j.LoggerFactory.getLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context.xml"})
public class HomePageTest {

    private static final Logger logger = getLogger(HomePageTest.class);

    @Autowired
    private OrgSyncHomePage homePage;

    @Autowired
    private AllMenuLocatorForOrgSync allMenuLocatorForOrgSync;

    @Before
    public void setup() {
        homePage.open();
    }

    @Test
    public void start() {
        homePage.getAllMenu().forEach(menu -> {
            try {
                menu.click();
                String title = homePage.until((AbstractPage input) -> homePage.getTitle());
                logger.info("clicking " + menu + " and title is \"" + title + "\"");
            } catch (Exception e) {
                logger.info("Error clicking " + menu, e);
            }
        });
    }


    @Test
    public void testBrowers() {
        for (Browser browser : EnumSet.of(CHROME, FIREFOX)) {
            new OrgSyncHomePage(browser, new Url(browser, "http://jqueryui.com/datepicker/"), allMenuLocatorForOrgSync).open();
        }
    }

    @After
    public void close() {
        homePage.close();
    }

}
