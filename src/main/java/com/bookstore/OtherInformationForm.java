package com.bookstore;


import com.algocrafts.pages.AbstractPage;
import com.bookstore.domain.MailingOptions;
import com.bookstore.domain.OtherInformation;

import static com.algocrafts.selectors.Name.MAILING_OPTION;
import static com.bookstore.BookStoreId.BILLING_EMAIL___;
import static com.bookstore.BookStoreId.COMMENTS________;
import static com.bookstore.BookStoreName.CONFIRM_EMAIL_;
import static com.bookstore.BookStoreName.RATINGS_______;

public class OtherInformationForm extends AbstractPage {

    public OtherInformationForm(AbstractPage page) {
        super(page);
    }

    public void setOtherInformation(OtherInformation info) {
        put(BILLING_EMAIL___, info.emailAddress);
        put(COMMENTS________, info.comments);
        check(CONFIRM_EMAIL_, info.confirmEmail);
        check(RATINGS_______, info.askRating);
        radio(MAILING_OPTION, info.mailingOptions);
    }

    public OtherInformation getOtherInformation() {
        return new OtherInformation(
                get(BILLING_EMAIL___),
                isChecked(CONFIRM_EMAIL_),
                isChecked(RATINGS_______),
                MailingOptions.valueOf(get(MAILING_OPTION)),
                get(COMMENTS________));
    }

}
