package com.algocrafts;


import com.algocrafts.clickables.Url;
import com.algocrafts.pages.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static com.algocrafts.selectors.CssSelector.SUBMIT;
import static com.algocrafts.selectors.Name.FILE;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context.xml"})
public class SpringUploadTest {

    @Autowired
    private Url uploadUrl;

    @Autowired
    private File uploadFile;

    private Page uploadPage;

    @Before
    public void openPage() {
        uploadPage = new Page(uploadUrl.load());
    }

    @Test
    public void fileUpload() {
        uploadPage.upload(FILE, SUBMIT, uploadFile);
        uploadPage.accept();
        //TODO to add assertion.
    }

    @After
    public void closeBrowser() {
        uploadPage.close();
    }
}



