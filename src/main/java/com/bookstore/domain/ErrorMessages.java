package com.bookstore.domain;


import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;

public class ErrorMessages {

    private final List<String> errorMessages;

    public ErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ErrorMessages(Stream<String> errorMessages) {
        this(newArrayList(errorMessages.iterator()));
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
