package com.bookstore.domain;


import java.util.Map;
import java.util.function.Function;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.stream.Stream.of;

public enum MailingOptions {

    Weekly_Newsletter("Weekly newsletter--New books, updates, news, and special offers"),
    Deal_Of_the_Day("Deal of the Day--These amazing special offers last just 24 hours!"),
    Both("Both"),
    No_Promotional_Mailers("No promotional mailers. I will still receive updates on my MEAPs and other books."),
    Keep_Me("Keep me on the lists I'm already on.");

    private final String string;

    private MailingOptions(String string) {
        this.string = string;
        MapHolder.map.put(string, this);
    }

    @Override
    public String toString() {
        return string;
    }

    /**
     * This method filtering the enum constants using the string and return the first one.
     *
     * @param string the string value
     * @return enum with the string value
     */
    public static MailingOptions fromString(String string) {
        return of(values()).filter((o) -> string.equals(o.string)).findFirst().get();
    }

    /**
     * This method look up the enum constant in the map.
     *
     * @param string
     * @return
     */
    public static MailingOptions from(String string) {
        return MapHolder.map.get(string);
    }

    private static class MapHolder {
        private static final Map<String, MailingOptions> map = newHashMap();
    }
}

