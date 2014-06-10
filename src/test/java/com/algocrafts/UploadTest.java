package com.algocrafts;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.selectors.CssSelector.SUBMIT;
import static com.algocrafts.selectors.Name.FILE;

public class UploadTest {

    private Browser browser = CHROME;
    private Page page;

    @Before
    public void loadPage() {
        browser.get("http://localhost:63342/seleniumcapsules/html/upload.html");
        page = new Page(browser);
    }

    @Test
    public void fileUpload() {
        page.upload(FILE, SUBMIT, new File("src/test/resources/upload/Test.log"));
        page.accept();
        //TODO to add assertion.
    }

    @After
    public void closeBrowser() {
        browser.quit();
    }
}


