
package com.algocrafts.pages;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Clickables<Where extends Searchable<Where>> implements Clickable {

    public static <Where extends Searchable<Where>> Clickable link(Where where, Locator<Where, Element> locator) {
        return new Clickables<>(where, locator);
    }

    public static <Where extends Searchable<Where>> Clickable menu(Where where, Locator<Where, Element> locator) {
        return new Clickables<>(where, locator);
    }

    public static <Where extends Searchable<Where>> Clickable button(Where where, Locator<Where, Element> locator) {
        return new Clickables<>(where, locator);
    }

    public static <Where extends Searchable<Where>> Clickable imageButton(Where where, String fileName, int index) {
        return new Clickables<>(where, input -> where.image(fileName, index));
    }

    private static final Logger log = getLogger(Clickables.class);
    private final Where where;
    private final Locator<Where, Element> locator;

    public Clickables(Where where, Locator<Where, Element> locator) {
        this.locator = locator;
        this.where = where;
    }

    public void click() {
        Element apply = locator.locate(where);
        log.info("clicking [" + apply + "]");
        where.save();
        apply.click();
        where.save();
    }

    @Override
    public String toString() {
        return locator.toString();
    }
}