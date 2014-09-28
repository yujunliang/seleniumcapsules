package com.algocrafts.chapter3.interfaces;

import com.algocrafts.pages.Page;
import com.algocrafts.selectors.LinkText;
import com.algocrafts.selectors.Xpath;
import com.algocrafts.selenium.SearchScope;

import java.util.stream.Stream;

public interface ChangeLocation extends SearchScope<Page> {

    default void changeLocation(LinkText first, LinkText second) {
        Stream.of(LinkText.CHANGE_LOCATION, first, second).forEach(
                linkText -> this.untilFound(linkText).click());
    }

        default String currentLocation() {
            return this.untilFound(Xpath.LOCATION).getText();
        }
}
