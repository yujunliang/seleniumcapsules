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
    private ErrorMessages errorMessages;

    @Autowired
    private ErrorMessages errorMessages2;

    @Autowired
    private BookstoreHomePage homePage;

    @Test
    public void invalidInput() {
        homePage.searchBook("Selenium WebDriver in Practice");

        new BookPage(homePage) {{
            secondAddToCart();
            gotoCart();
        }};

        ShoppingCartPage cartPage = new ShoppingCartPage(homePage) {{
            setQuantity(2);
            setBillingAddress(billingAddress);
            setCreditCard(creditCard);
            setOtherInformation(otherInformation);
            continues();
        }};

        assertEquals(errorMessages, cartPage.getErrorMessages());
    }

    @Test
    public void invalidInputNormalWay() {
        homePage.searchBook("Selenium WebDriver in Practice");

        BookPage bookPage = new BookPage(homePage);
        bookPage.addToCart();
        bookPage.gotoCart();

        ShoppingCartPage cartPage = new ShoppingCartPage(homePage);
        cartPage.setQuantity(2);
        cartPage.setShippingAddress(billingAddress);
        cartPage.setCreditCard(creditCard);
        cartPage.setOtherInformation(otherInformation);
        cartPage.continues();

        assertEquals(errorMessages2, cartPage.getErrorMessages());
    }


    @Before
    public void setup() {
        homePage.open();
    }

    @After
    public void close() {
 //       homePage.close();
    }

}
