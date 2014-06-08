package com.bookstore;


import com.algocrafts.domain.CreditCardType;
import com.algocrafts.pages.AbstractPage;
import com.bookstore.domain.CreditCard;

import java.time.Month;

import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.bookstore.BookStoreId.*;

public class CreditCardForm extends AbstractPage {

    public CreditCardForm(AbstractPage page) {
        super(page);
    }

    public void setCreditCard(CreditCard card) {
        put(CARD_CVV, card.cardCvv);
        put(CARD_NUMBER, card.cardNumber);
        select(CARD_TYPE, card.cardType);
        select(CARD_EXP_MONTH, card.expirationMonth);
        select(CARD_EXP_YEAR, card.expirationYear);
    }

    public CreditCard getCreditCard() {
        return new CreditCard(
                get(CARD_TYPE, CreditCardType::fromString),
                get(CARD_NUMBER),
                get(CARD_CVV),
                get(CARD_EXP_MONTH, Month::valueOf),
                get(CARD_EXP_YEAR, PARSE_INT));

    }

}
