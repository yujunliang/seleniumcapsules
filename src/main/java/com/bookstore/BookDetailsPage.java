package com.bookstore;


import com.algocrafts.clickables.ImageButton;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Clickable;


public class BookDetailsPage extends Page {

    public BookDetailsPage(Page page) {
        super(page);
    }

    public Clickable addToCart() {
        return new ImageButton<>(this, "addtocart.gif", 0);
    }

    public Clickable secondAddToCart() {
        return new ImageButton<>(this, "addtocart.gif", 1);
    }

}
