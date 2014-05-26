package com.algocrafts.forms;

import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.pages.Locator;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.openqa.selenium.By.name;

public class AbstractForm implements Searchable<AbstractForm>, FormControl<AbstractForm> {

    protected final AbstractPage page;
    private final Locator<AbstractPage, Element> locator;

    public AbstractForm(AbstractPage page, String name) {
        this(page, new ElementLocator<>(() -> name(name)));
    }

    public AbstractForm(AbstractPage page, Locator<AbstractPage, Element> locator) {
        this.page = page;
        this.locator = locator;
    }

    @Override
    public void save() {
        page.save();
    }

    @Override
    public Element findElement(By by) {
        return locator.apply(page).findElement(by);
    }

    @Override
    public Element untilFound(By by) {
        return locator.apply(page).untilFound(by);
    }

    @Override
    public Stream<Element> findElements(Supplier<By> by) {
        return locator.apply(page).findElements(by).map(Element::new);
    }
}
