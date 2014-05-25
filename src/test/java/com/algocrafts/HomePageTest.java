package com.algocrafts;


import com.algocrafts.pages.AbstractPage;
import com.orgsync.OrgSyncHomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.slf4j.LoggerFactory.getLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context.xml"})
public class HomePageTest {

    private static final Logger logger = getLogger(HomePageTest.class);

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
                String title = homePage.until((AbstractPage input) -> homePage.getTitle());
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

}
