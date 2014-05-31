package com.bookstore;


import com.algocrafts.forms.AbstractForm;
import com.algocrafts.pages.AbstractPage;
import com.bookstore.domain.OtherInformation;

import static com.algocrafts.selectors.Name.MAILING_OPTION;
import static com.bookstore.BookStoreId.*;

public class OtherInformationForm extends AbstractForm {

    public OtherInformationForm(AbstractPage page) {
        super(page);
    }

    public void setOtherInformation(OtherInformation info) {
        put(BILLING_EMAIL___, info.emailAddress);
        put(COMMENTS________, info.comments);
        check(RATINGS_______, info.askRating);
        check(CONFIRM_EMAIL_, info.confirmEmail);
        radio(MAILING_OPTION, info.mailingOptions);
    }

}
