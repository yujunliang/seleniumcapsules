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

    private final BillingAddressForm billingAddressForm = new BillingAddressForm(this);
    private final CreditCardForm creditCardForm = new CreditCardForm(this);

    public ShoppingCartPage(AbstractPage page) {
        super(page);
    }

    public void setBillingAddress(Address address) {
        billingAddressForm.setBillingAddress(address);
    }

    public void getBillingAddress() {
        billingAddressForm.getBillingAddress();
    }

    public void setCreditCard(CreditCard card) {
        creditCardForm.setCreditCard(card);
    }

    public void setQuantity(int quantity) {
        put(Xpath.QUANTITY, quantity);
        button(UPDATE).click();
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
