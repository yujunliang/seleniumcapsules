package com.bookstore;


import com.algocrafts.pages.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selectors.Xpath;
import com.bookstore.domain.Address;
import com.bookstore.domain.CreditCard;
import com.bookstore.domain.ErrorMessages;
import com.bookstore.domain.OtherInformation;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.pages.Locators.elements;
import static com.algocrafts.selectors.CssSelector.CONTINUE;
import static com.algocrafts.selectors.CssSelector.UPDATE;
import static com.algocrafts.selectors.Name.MAILING_OPTION;
import static com.algocrafts.selectors.TagName.LI;
import static com.bookstore.BookStoreId.*;

public class ShoppingCartPage extends AbstractPage {

    public ShoppingCartPage(AbstractPage page) {
        super(page);
    }

    public void setQuantity(int quantity) {
        put(Xpath.QUANTITY, quantity);
        button(UPDATE).click();
    }

    public void setBillingAddress(Address address) {
        put(BILLING_FIRST_NAME, address.firstName);
        put(BILLING_LAST_NAME_, address.lastName);
        put(BILLING_ADDRESS1__, address.street1);
        put(BILLING_ADDRESS2__, address.street2);
        put(BILLING_CITY______, address.city);
        put(BILLING_STATE_____, address.state);
        put(BILLING_ZIP_______, address.zipcode);
        select(BILLING_COUNTRY___, address.country);
    }

    public void setCreditCard(CreditCard card) {
        put(CARD_CVV__________, card.cardCvv);
        put(CARD_NUMBER_______, card.cardNumber);
        select(CARD_TYPE_________, card.cardType);
        select(CARD_EXP_MONTH____, card.expirationMonth);
        select(CARD_EXP_YEAR_____, card.expirationYear);
    }

    public void setOtherInformation(OtherInformation info) {
        put(BILLING_EMAIL___, info.emailAddress);
        put(COMMENTS________, info.comments);
        check(RATINGS_______, info.askRating);
        check(CONFIRM_EMAIL_, info.confirmEmail);
        radio(MAILING_OPTION, info.mailingOptions);
    }

    public void continues() {
        button(CONTINUE).click();
    }

    public ErrorMessages getErrorMessages() {
        return new ErrorMessages(Locators.<AbstractPage>element(ERROR_MESSAGES)
                .and(elements(LI)).locate(this).map(TEXT));
    }
}
