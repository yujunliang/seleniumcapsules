package com.algocrafts.forms;

import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locating;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This class is need when there are multiple forms on the same page and there are fields with the same
 * name appears in multiple forms. To locate the particular element using name, we need to use this AbstractForm
 * to encapsulate each form.
 */
public class AbstractForm extends Locating<AbstractPage, Element> implements Searchable<Element>, FormControl<Element> {

    public AbstractForm(AbstractPage page, Locator<AbstractPage, Element> locator) {
        super(page, locator);
    }

    @Override
    public void save() {
        where.save();
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
