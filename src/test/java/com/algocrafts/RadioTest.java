package com.algocrafts;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.pages.Page;
import com.bookstore.domain.MailingOptions;
import org.junit.Test;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.selectors.Name.MAILING_OPTION;
import static com.bookstore.domain.MailingOptions.*;
import static org.junit.Assert.assertEquals;

public class RadioTest {
    @Test
    public void testRadio() {
        Browsers chrome = CHROME;
        chrome.get("http://localhost:63342/seleniumcapsules/html/radio.html");
        Page page = new Page(chrome);
        page.setRadioButton(MAILING_OPTION, No_Promotional_Mailers);

        assertEquals(No_Promotional_Mailers, fromString(page.getRadio(MAILING_OPTION)));
        assertEquals(No_Promotional_Mailers, from(page.getRadio(MAILING_OPTION)));
        assertEquals(No_Promotional_Mailers, page.getRadio(MAILING_OPTION, (s) -> from(s)));
        assertEquals(No_Promotional_Mailers, page.getRadio(MAILING_OPTION, MailingOptions::from));
    }
}
