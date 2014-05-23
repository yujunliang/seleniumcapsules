package com.algocrafts.converters;

import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Locator;

public enum PageFunctions implements Locator<AbstractPage, String> {
    THE_PAGE_TITLE {
        @Override
        public String apply(AbstractPage page) {
            return page.getTitle();
        }
    };
}
