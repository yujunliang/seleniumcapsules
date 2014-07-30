package com.algocrafts.converters;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

public enum PageFunctions implements Locator<Page, String> {
    THE_PAGE_TITLE {
        @Override
        public String locate(Page page) {
            return page.getTitle();
        }
    };
}
