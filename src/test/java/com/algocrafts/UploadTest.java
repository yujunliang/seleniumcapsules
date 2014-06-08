package com.algocrafts;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import org.junit.Test;

import java.io.File;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.selectors.CssSelector.SUBMIT;
import static com.algocrafts.selectors.Name.FILE;

public class UploadTest {

    @Test
    public void testReadFromTable() {
        Browser browser = CHROME;
        browser.get("http://localhost:63342/seleniumcapsules/html/upload.html");
        Page page = new Page(browser);
        page.upload(FILE, SUBMIT, new File("src/test/resources/upload/Test.log"));

        page.accept();
    }
}
