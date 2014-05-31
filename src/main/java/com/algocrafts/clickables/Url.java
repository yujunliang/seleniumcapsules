package com.algocrafts.clickables;

import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Url<T extends Browser> implements Clickable {

    private static final Logger log = getLogger(Url.class);

    private T browser;
    private String url;

    public Url(T browser, String url) {
        this.browser = browser;
        this.url = url;
    }

    public void click() {
        log.info("loading [" + url + "]");
        browser.get(url);
        browser.save(url);
    }
}
