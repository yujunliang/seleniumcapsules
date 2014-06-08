package com.bookstore;


import com.algocrafts.pages.AbstractPage;
import com.bookstore.domain.MailingOptions;
import com.bookstore.domain.OtherInformation;

import static com.algocrafts.selectors.Name.MAILING_OPTION;
import static com.bookstore.BookStoreId.*;

public class OtherInformationForm extends AbstractPage {

    public OtherInformationForm(AbstractPage page) {
        super(page);
    }

    public void setOtherInformation(OtherInformation info) {
        put(BILLING_EMAIL, info.emailAddress);
        put(COMMENTS, info.comments);
        check(CONFIRM_EMAIL, info.confirmEmail);
        check(RATINGS, info.askRating);
        setRadio(MAILING_OPTION, info.mailingOptions);
    }

    public OtherInformation getOtherInformation() {
        return new OtherInformation(
                get(BILLING_EMAIL),
                isChecked(CONFIRM_EMAIL),
                isChecked(RATINGS),
                get(MAILING_OPTION, MailingOptions::from),
                get(COMMENTS)
        );
    }

}
