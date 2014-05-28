package com.algocrafts.clickables;

import com.algocrafts.pages.Browsers;
import com.algocrafts.selenium.Clickable;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import static org.slf4j.LoggerFactory.getLogger;


public class Url implements Clickable {

    private static final Logger log = getLogger(Url.class);

    @Value("${browser}")
    private Browsers browser;
    private String url;

    public Url(String url) {
        this.url = url;
    }

    public void click() {
        log.info("loading [" + url + "]");
        browser.get(url);
        browser.save(url);
    }
}
