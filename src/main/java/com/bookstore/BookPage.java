package com.bookstore;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.TagName;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Element;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;

public class BookPage extends Page {

    private Predicate<Page> colorBecomeWhite = (Page b) -> cartButton().getCssValue("color").equals("rgba(255, 255, 255, 1)");

    public BookPage(Browsers browser, Clickable url) {
        super(browser, url);
    }

    public void addToCart() {
        findElements(TagName.INPUT).filter((e) -> e.getAttribute("value").equals("add to cart")).findFirst().get().click();
        new FluentWait<Page>(this).until(colorBecomeWhite) ;
    }

    public void secondAddToCart() {
        ((Element) findElements(TagName.INPUT).filter((e) -> e.getAttribute("value").equals("add to cart")).toArray()[1]).click();
        new FluentWait<Page>(this).until(colorBecomeWhite) ;
    }

    public Element cartButton() {
        return untilFound(() -> By.xpath("//*[@id=\"primary-navbar\"]/ul[2]/li[2]/a"));
    }

}
