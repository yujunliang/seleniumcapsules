package com.bookstore.domain;


import java.util.Map;

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

    public static MailingOptions fromString(String string) {
        return of(values()).filter((o) -> string.equals(o.string)).findFirst().get();
    }

    public static MailingOptions from(String string) {
        return MapHolder.map.get(string);
    }

    private static class MapHolder {
        private static final Map<String, MailingOptions> map = newHashMap();
    }
}

