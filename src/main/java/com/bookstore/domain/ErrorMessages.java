package com.bookstore.domain;


import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Locators;

import java.util.List;
import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.selenium.Locators.elements;
import static com.algocrafts.selectors.TagName.LI;
import static com.bookstore.BookStoreId.ERROR_MESSAGES;
import static java.util.stream.Collectors.toList;

public class ErrorMessages {

    private final List<String> errorMessages;

    public ErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ErrorMessages(Stream<String> errorMessages) {
        this(errorMessages.collect(toList()));
    }

    public ErrorMessages(AbstractPage page) {
        this(Locators.<AbstractPage>element(ERROR_MESSAGES)
                .and(elements(LI)).locate(page).map(TEXT));
    }

    @Override
    public boolean equals(Object other) {
        return this == other ||
            (other instanceof ErrorMessages &&
                ((ErrorMessages) other).errorMessages.equals(this.errorMessages)
            );
    }

    @Override
    public String toString() {
        return errorMessages.toString();
    }
}
