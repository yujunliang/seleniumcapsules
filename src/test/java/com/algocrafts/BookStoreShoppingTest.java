package com.algocrafts;


import com.algocrafts.selectors.Name;
import com.bookstore.BookDetailsPage;
import com.bookstore.BookListPage;
import com.bookstore.BookStoreHomePage;
import com.bookstore.ShoppingCartPage;
import com.bookstore.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.algocrafts.conditions.PagePredicates.IS_COPYRIGHTED;
import static com.algocrafts.selectors.LinkText.ACTIVE_MQ_IN_ACTION;
import static com.algocrafts.selectors.LinkText.JAVA;
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
    public void invalidCardInfo() {

        BookListPage listPage = new BookListPage(homePage, homePage.link(JAVA), IS_COPYRIGHTED) {{
            open();
            link(ACTIVE_MQ_IN_ACTION).click();
        }};
        BookDetailsPage bookPage = new BookDetailsPage(listPage) {{
            until(IS_COPYRIGHTED);
            secondAddToCart().click();
        }};

        ShoppingCartPage cartPage = new ShoppingCartPage(bookPage) {{
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

        BookListPage listPage = new BookListPage(homePage, homePage.link(JAVA), IS_COPYRIGHTED);
        listPage.open();
        listPage.link(ACTIVE_MQ_IN_ACTION).click();

        BookDetailsPage bookPage = new BookDetailsPage(listPage);
        bookPage.until(IS_COPYRIGHTED);
        bookPage.secondAddToCart().click();

        ShoppingCartPage cartPage = new ShoppingCartPage(bookPage);
        cartPage.setQuantity(2);
        cartPage.setBillingAddress(billingAddress);
        cartPage.setCreditCard(creditCard);
        cartPage.setOtherInformation(otherInformation);
        cartPage.continues();

        cartPage.radio(() -> By.name("customFieldDS.customfield_ROW0_value"), "No promotional mailers. I will still receive updates on my MEAPs and other books.");

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
