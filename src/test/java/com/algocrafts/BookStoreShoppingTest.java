package com.algocrafts;


import com.bookstore.BookPage;
import com.bookstore.BookstoreHomePage;
import com.bookstore.ShoppingCartPage;
import com.bookstore.domain.Address;
import com.bookstore.domain.CreditCard;
import com.bookstore.domain.ErrorMessages;
import com.bookstore.domain.OtherInformation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:bookstore/beans/context.xml"})
public class BookStoreShoppingTest {

    @Autowired
    private Address billingAddress;

    @Autowired
    private CreditCard creditCard;

    @Autowired
    private OtherInformation otherInformation;

    @Autowired
    private ErrorMessages expectedErrorMessages;

    @Autowired
    private BookstoreHomePage homePage;

    @Test
    public void invalidCardInfo() {
        homePage.open();
        homePage.searchBook("Selenium WebDriver in Practice");

        new BookPage(homePage) {{
            secondAddToCart();
            cartButton().click();
        }};

        ShoppingCartPage cartPage = new ShoppingCartPage(homePage) {{
            setQuantity(2);
            setBillingAddress(billingAddress);
            setCreditCard(creditCard);
            setOtherInformation(otherInformation);
            continues();
        }};

        assertEquals(expectedErrorMessages, cartPage.getErrorMessages());
    }

    @Test
    public void invalidCardInfoNormalWay() {
        homePage.open();
        homePage.searchBook("Selenium WebDriver in Practice");

        BookPage bookPage = new BookPage(homePage);
        bookPage.secondAddToCart();
        bookPage.cartButton().click();

        ShoppingCartPage cartPage = new ShoppingCartPage(homePage);
        cartPage.setQuantity(2);
        cartPage.setBillingAddress(billingAddress);
        cartPage.setCreditCard(creditCard);
        cartPage.setOtherInformation(otherInformation);
        cartPage.continues();

        assertEquals(expectedErrorMessages, cartPage.getErrorMessages());
    }


    @Before
    public void setup() {
        homePage.open();
    }

    @After
    public void close() {
        homePage.close();
    }

}
