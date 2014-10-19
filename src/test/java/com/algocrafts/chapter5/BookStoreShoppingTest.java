package com.algocrafts.chapter5;


import com.algocrafts.selenium.Element;
import com.bookstore.BookDetailsPage;
import com.bookstore.BookListPage;
import com.bookstore.BookStoreHomePage;
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

import static com.algocrafts.conditions.PagePredicates.IS_COPYRIGHTED;
import static com.algocrafts.selectors.LinkText.ACTIVE_MQ_IN_ACTION;
import static com.algocrafts.selectors.LinkText.JAVA;
import static com.bookstore.BookStoreId.*;
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
    private BookStoreHomePage homePage;

    @Test
    public void invalidCardInfoNormalWay() {

        BookListPage listPage = new BookListPage(homePage, homePage.link(JAVA));
        listPage.open();
        listPage.link(ACTIVE_MQ_IN_ACTION).click();

        BookDetailsPage bookPage = new BookDetailsPage(listPage);
        bookPage.until(IS_COPYRIGHTED);
        bookPage.secondAddToCart().click();

        ShoppingCartPage cartPage = new ShoppingCartPage(bookPage);
        cartPage.setQuantity(2);

        Element element = cartPage.untilFound(BILLING_FIRST_NAME);
        element.clear();
        element.sendKeys(billingAddress.firstName);

        element = cartPage.untilFound(BILLING_LAST_NAME);
        element.clear();
        element.sendKeys(billingAddress.lastName);

        element = cartPage.untilFound(BILLING_ADDRESS1);
        element.clear();
        element.sendKeys(billingAddress.street1);


        cartPage.put(BILLING_LAST_NAME, billingAddress.lastName);
        cartPage.put(BILLING_ADDRESS1, billingAddress.street1);
        cartPage.put(BILLING_ADDRESS2, billingAddress.street2);
        cartPage.put(BILLING_CITY, billingAddress.city);
        cartPage.put(BILLING_STATE, billingAddress.state);
        cartPage.put(BILLING_ZIP, billingAddress.zipcode);
        cartPage.select(BILLING_COUNTRY, billingAddress.country);

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
