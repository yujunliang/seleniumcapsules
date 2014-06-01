package com.bookstore;


import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selectors.Xpath;
import com.bookstore.domain.Address;
import com.bookstore.domain.CreditCard;
import com.bookstore.domain.ErrorMessages;
import com.bookstore.domain.OtherInformation;

import static com.algocrafts.selectors.CssSelector.CONTINUE;
import static com.algocrafts.selectors.CssSelector.UPDATE;

public class ShoppingCartPage extends AbstractPage {

    private final BillingAddressForm billingAddressForm = new BillingAddressForm(this);
    private final CreditCardForm creditCardForm = new CreditCardForm(this);
    private final OtherInformationForm otherInformationForm = new OtherInformationForm(this);

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

    public CreditCard getCreditCard() {
        return creditCardForm.getCreditCard();
    }

    public void setQuantity(int quantity) {
        put(Xpath.QUANTITY, quantity);
        button(UPDATE).click();
    }

    public void setOtherInformation(OtherInformation info) {
        otherInformationForm.setOtherInformation(info);
    }

    public OtherInformation getOtherInformation() {
        return otherInformationForm.getOtherInformation();
    }

    public void continues() {
        button(CONTINUE).click();
    }

    public ErrorMessages getErrorMessages() {
         return new ErrorMessages(this);
    }
}
