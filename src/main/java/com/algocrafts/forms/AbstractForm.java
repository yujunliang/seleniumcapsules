package com.algocrafts.forms;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locating;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This class is need when there are multiple forms on the same page and there are fields with the same
 * name appears in multiple forms. To locate the particular element using name, we need to use this AbstractForm
 * to encapsulate each form.
 */
public class AbstractForm extends Locating<Page, Element> implements SearchScope<Element>, FormControl<Element> {

    public AbstractForm(Page page, Locator<Page, Element> locator) {
        super(page, locator);
    }

    @Override
    public void onTimeout() {
        where.onTimeout();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return locate().findElements(by);
    }

    @Override
    public Element findElement(By by) {
        return locate().findElement(by);
    }

}
