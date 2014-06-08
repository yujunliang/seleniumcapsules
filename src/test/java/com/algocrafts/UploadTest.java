package com.algocrafts;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.CssSelector;
import com.algocrafts.selectors.Name;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.table.Table;
import com.algocrafts.table.TableContents;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;
import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.CssSelector.SUBMIT;
import static com.algocrafts.selectors.Id.MAIN;
import static com.algocrafts.selectors.Name.FILE;
import static com.algocrafts.selectors.TagName.TABLE;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class UploadTest {

    @Test
    public void testReadFromTable() {
        Browser browser = Browsers.CHROME;
        browser.get("http://localhost:63342/seleniumcapsules/html/upload.html");
        Page page = new Page(browser);
        page.upload(FILE, new File("src/test/resources/upload/Test.log"));
        page.button(SUBMIT).click();

        page.accept();
    }
}
