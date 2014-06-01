package com.algocrafts.forms;

import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AbstractForm implements Searchable<Element>, FormControl<Element> {

    private final AbstractPage page;
    private final Locator<AbstractPage, Element> locator;

    public AbstractForm(AbstractPage page, Locator<AbstractPage, Element> locator) {
        this.page = page;
        this.locator = locator;
    }

    @Override
    public void save() {
        page.save();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return locator.locate(page).findElements(by);
    }

    @Override
    public Element findElement(By by) {
        return locator.locate(page).findElement(by);
    }

}
