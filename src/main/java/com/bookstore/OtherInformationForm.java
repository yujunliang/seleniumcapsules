package com.bookstore;


import com.algocrafts.pages.Page;
import com.bookstore.domain.MailingOptions;
import com.bookstore.domain.OtherInformation;

import static com.algocrafts.selectors.Name.MAILING_OPTION;
import static com.bookstore.BookStoreId.*;

public class OtherInformationForm  {

    private Page page;

    public OtherInformationForm(Page page) {
        this.page = page;
    }

    public void setOtherInformation(OtherInformation info) {
        page.put(COUPON_CODE, info.couponCode);
        page.put(BILLING_EMAIL, info.emailAddress);
        page.put(COMMENTS, info.comments);
        page.setCheckbox(CONFIRM_EMAIL, info.confirmEmail);
        page.setCheckbox(RATINGS, info.askRating);
        page.setRadioButton(MAILING_OPTION, info.mailingOptions);
    }

    public OtherInformation getOtherInformation() {
        return new OtherInformation(
                page.get(COUPON_CODE),
                page.get(BILLING_EMAIL),
                page.isChecked(CONFIRM_EMAIL),
                page.isChecked(RATINGS),
                page.get(MAILING_OPTION, MailingOptions::from),
                page.get(COMMENTS)
        );
    }

}
