package com.bookstore;


import com.algocrafts.domain.CreditCardType;
import com.algocrafts.pages.Page;
import com.bookstore.domain.CreditCard;

import java.time.Month;

import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.bookstore.BookStoreId.*;

public class CreditCardForm  {

    private Page page;

    public CreditCardForm(Page page) {
        this.page = page;
    }

    public void setCreditCard(CreditCard card) {
        page.put(CARD_CVV, card.cardCvv);
        page.put(CARD_NUMBER, card.cardNumber);
        page.select(CARD_TYPE, card.cardType);
        page.select(CARD_EXP_MONTH, card.expirationMonth);
        page.select(CARD_EXP_YEAR, card.expirationYear);
    }

    public CreditCard getCreditCard() {
        return new CreditCard(
                page.get(CARD_TYPE, CreditCardType::fromString),
                page.get(CARD_NUMBER),
                page.get(CARD_CVV),
                page.get(CARD_EXP_MONTH, Month::valueOf),
                page.get(CARD_EXP_YEAR, PARSE_INT));

    }

}
