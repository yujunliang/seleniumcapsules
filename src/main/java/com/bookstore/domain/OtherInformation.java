package com.bookstore.domain;


public class OtherInformation {
    public final  String couponCode;
    public final String emailAddress;
    public final boolean askRating;
    public final MailingOptions mailingOptions;
    public final boolean confirmEmail;
    public final String comments;

    public OtherInformation(String couponCode,
                            String emailAddress,
                            boolean confirmaEmail,
                            boolean askRating,
                            MailingOptions mailingOptions,
                            String comments) {
        this.couponCode = couponCode;
        this.emailAddress = emailAddress;
        this.confirmEmail = confirmaEmail;
        this.askRating = askRating;
        this.mailingOptions = mailingOptions;
        this.comments = comments;
    }
}
