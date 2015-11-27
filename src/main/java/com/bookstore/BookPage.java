package com.bookstore;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.clickables.ImageButton;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.TagName;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Element;

public class BookPage extends Page {

    public BookPage(Browsers browser, Clickable url) {
        super(browser, url);
    }

    public void addToCart() {
        findElements(TagName.INPUT).filter((e) -> e.getAttribute("value").equals("add to cart")).findFirst().get().click();
    }

    public void secondAddToCart() {
        Element value = (Element) findElements(TagName.INPUT).filter((e) -> e.getAttribute("value").equals("add to cart")).toArray()[1];
        value.click();
    }

}
