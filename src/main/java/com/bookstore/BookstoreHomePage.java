package com.bookstore;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Clickable;
import org.openqa.selenium.By;

public class BookstoreHomePage extends Page {

    public BookstoreHomePage(Browsers browser, Clickable url) {
        super(browser, url);
    }

    public void searchBook(String bookname) {
        put(() -> By.id("navbar-search"), bookname);
        untilFound(() -> By.xpath("//*[@id=\"desktop-search-form\"]/div/div/span/button")).click();
        untilFound(() -> By.partialLinkText(bookname)).click();
    }

}
