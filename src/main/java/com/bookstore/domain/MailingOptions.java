package com.bookstore.domain;


public enum MailingOptions {

    Weekly_Newsletter("Weekly newsletter--New books, updates, news, and special offers"),
    Deal_Of_the_Day("Deal of the Day--These amazing special offers last just 24 hours!"),
    Both("Both"),
    No_Promotional_Mailers("No promotional mailers. I will still receive updates on my MEAPs and other books."),
    Keep_Me("Keep me on the lists I'm already on.");

    private final String string;
    MailingOptions(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
