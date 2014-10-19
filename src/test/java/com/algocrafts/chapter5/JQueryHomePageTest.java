package com.algocrafts.chapter5;


import com.jquery.JQueryHomePage;
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
public class JQueryHomePageTest {

    private static final Logger logger = getLogger(JQueryHomePageTest.class);

    @Autowired
    private JQueryHomePage jQueryHomePage;


    @Before
    public void setup() {
        jQueryHomePage.open();
    }

    @Test
    public void start() {
        jQueryHomePage.getAllMenu().forEach(menu -> {
            try {
                logger.info("clicking " + menu);
                jQueryHomePage.open();
                menu.click();
                String title =  jQueryHomePage.getTitle();
                logger.info("clicking " + menu + " and title is \"" + title + "\"");
            } catch (Exception e) {
                logger.info("Error clicking " + menu, e);
            }
        });
    }

    @After
    public void close() {
        jQueryHomePage.close();
    }

}
