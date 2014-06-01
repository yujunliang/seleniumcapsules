package com.bookstore;


import com.algocrafts.pages.AbstractPage;
import com.bookstore.domain.CreditCard;

import java.time.Month;

import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.algocrafts.domain.CreditCardType.fromString;
import static com.bookstore.BookStoreId.*;

public class CreditCardForm extends AbstractPage {

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

    public CreditCard getCreditCard() {
        return new CreditCard(
                fromString(get(CARD_TYPE_________)),
                get(CARD_NUMBER_______),
                get(CARD_CVV__________),
                Month.valueOf(get(CARD_EXP_MONTH____)),
                PARSE_INT.locate(get(CARD_EXP_YEAR_____)));

    }

}
