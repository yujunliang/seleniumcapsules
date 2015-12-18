package com.bookstore;


import com.algocrafts.pages.Page;
import com.algocrafts.selectors.TagName;
import com.algocrafts.selenium.Element;
import com.google.common.base.Predicate;
import org.openqa.selenium.support.ui.FluentWait;

import static com.algocrafts.selectors.Xpath.CART_BUTTON;

public class BookPage extends Page {

    private Predicate<BookPage> colorBecomeWhite = (BookPage b) -> cartButton().getCssValue("color").equals("rgba(255, 255, 255, 1)");

    public BookPage(Page page) {
        super(page);
    }

    public void addToCart() {
        findElements(TagName.INPUT).filter((e) -> e.getAttribute("value").equals("add to cart")).findFirst().get().click();
        new FluentWait<>(this).until(colorBecomeWhite) ;
    }

    public void secondAddToCart() {
        ((Element) findElements(TagName.INPUT).filter((e) -> e.getAttribute("value").equals("add to cart")).toArray()[1]).click();
        new FluentWait<>(this).until(colorBecomeWhite);
    }

    public void gotoCart() {
       cartButton().click();
    }

    public Element cartButton() {
        return untilFound(CART_BUTTON);
    }

}
