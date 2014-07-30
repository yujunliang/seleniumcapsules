package com.algocrafts.converters;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

public class FrameLocator implements Locator<Page, Page> {

    private final int index;

    public FrameLocator(int index) {
        this.index = index;
    }

    @Override
    public Page locate(Page page) {
        return page.frame(index);
    }

    @Override
    public String toString() {
        return "frames[" + index + "]";
    }
}