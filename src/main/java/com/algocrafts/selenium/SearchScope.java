package com.algocrafts.selenium;

import com.algocrafts.clickables.Button;
import com.algocrafts.clickables.Link;
import com.algocrafts.conditions.StringContains;
import com.algocrafts.converters.ElementAtIndex;
import com.algocrafts.converters.Filter;
import com.algocrafts.converters.FirstItem;
import com.algocrafts.converters.StreamToList;
import com.algocrafts.locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_NULL;
import static com.algocrafts.converters.GetText.SRC;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.TagName.IMG;


public interface SearchScope<Where extends SearchScope<Where>> extends SearchContext, ExplicitWait<Where> {

    /**
     * Find the first element or throw NoSuchElementException
     *
     * @param by selector
     * @return the first element or throw NoSuchElementException
     */
    @Override
    Element findElement(By by);

    /**
     * Find the first element or return empty Optional if nothing found.
     *
     * @param by selector
     * @return the first element or return empty Optional if nothing found.
     */
    default public Optional<Element> optionalElement(Supplier<By> by) {
        try {
            return  Optional.of(findElement(by.get()));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    /**
     * Find the first element until timeout then throw NoSuchElementException
     *
     * @param by selector
     * @return the first element or throw NoSuchElementException
     */
    default public Element untilFound(Supplier<By> by) {
        return until((Where page) -> findElement(by.get()));
    }

    /**
     * Find all elements within the area using the given search method.
     *
     * @param by selector
     * @return A stream of all {@link Element}s, or an empty stream if nothing matches.
     * @see org.openqa.selenium.By
     */
    default public Stream<Element> findElements(Supplier<By> by) {
        return findElements(by.get()).stream().map(Element::new);
    }

    /**
     * Find the first button meeting the By selector.
     * method to find the button.
     *
     * @param by selector                       ˜
     * @return the first button meeting the By selector.
     */
    default public Clickable button(Supplier<By> by) {
        return button(by, 0);
    }

    /**
     * If there are multiple buttons with the same name on the same page, use this
     * method to find the button.
     *
     * @param by    selector
     * @param index given index
     * @return the button by index in the list
     */
    @SuppressWarnings("unchecked")
    default public Clickable button(Supplier<By> by, int index) {
        return new Button<>((Where) this, Locators.<Where>elements(by)
                .and(new StreamToList<>())
                .and(new ElementAtIndex<>(index)));
    }

    /**
     * If the button can't be found using the previous two methods, use this.
     *
     * @param locator locator
     * @return button found by the locator
     */
    @SuppressWarnings("unchecked")
    default public Clickable button(Locator<Where, Element> locator) {
        return new Button<>((Where) this, locator);
    }

    /**
     * The first image using the image file.
     *
     * @param fileName file name
     * @return first image found by using the image file.
     */
    default public Optional<Element> image(String fileName) {
        return new FirstItem<Element>().locate(images(fileName));
    }

    /**
     * The image at the given index using the same image file.
     *
     * @param fileName file name
     * @param index    index
     * @return image at the given index using the same image file.
     */
    default public Element image(String fileName, int index) {
        return new StreamToList<Element>()
                .and(new ElementAtIndex<>(index))
                .locate(images(fileName));
    }

    /**
     * Find the images using the same image file.
     *
     * @param fileName file name
     * @return the images  found by using the same image file.
     */
    default public Stream<Element> images(String fileName) {
        return until(Locators.<Where>elements(IMG)
                        .and(new Filter<>(NOT_NULL.and(DISPLAYED).and(SRC.and(new StringContains(fileName)))))
        );
    }

    /**
     * Find the link using the selector.
     *
     * @param selector selector
     * @return the link found by using the selector.
     */
    @SuppressWarnings("unchecked")
    default public Clickable link(Supplier<By> selector) {
        return new Link<>((Where) this, element(selector));
    }

}
