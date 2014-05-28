package com.algocrafts.forms;

import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.pages.Locators.element;
import static org.openqa.selenium.By.name;

public class AbstractForm implements Searchable<AbstractForm>, FormControl<AbstractForm> {

    protected final AbstractPage page;
    private final Locator<AbstractPage, Element> locator;

    public AbstractForm(AbstractPage page, String name) {
        this(page, element(() -> name(name)));
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
        return locator.locate(page).findElement(by);
    }

    @Override
    public Element untilFound(By by) {
        return locator.locate(page).untilFound(by);
    }

    @Override
    public Stream<Element> findElements(Supplier<By> by) {
        return locator.locate(page).findElements(by).map(Element::new);
    }
}
