package com.bookstore;


import com.algocrafts.forms.AbstractForm;
import com.algocrafts.pages.AbstractPage;
import com.bookstore.domain.CreditCard;

import static com.bookstore.BookStoreId.*;

public class CreditCardForm extends AbstractForm {

    public CreditCardForm(AbstractPage page) {
        super(page);
    }

    public void setCreditCard(CreditCard card) {
        put(CARD_CVV__________, card.cardCvv);
        put(CARD_NUMBER_______, card.cardNumber);
        select(CARD_TYPE_________, card.cardType);
        select(CARD_EXP_MONTH____, card.expirationMonth);
        select(CARD_EXP_YEAR_____, card.expirationYear);
    }
}
