seleniumcapsules
================

Selenium Capsules - Encapsulating Selenium

Wrapper of Selenium WebDriver API with Functional Programming feature from Java 8.


Do you like this test?


    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void autoCompeleteUsingSelenium() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://google.com");
        WebElement q = driver.findElement(By.name("q"));

        q.clear();
        WebElement oracle = null;
        for (char c : "oracle".toCharArray()) {
            q.sendKeys(String.valueOf(c));
            try {
                oracle = driver.findElement(
                        By.xpath("//table[contains(@class, 'gssb_c')]/descendant::span[text()='oracle']"));
                oracle.click();
            } catch (NoSuchElementException e) {
                log.debug("This is OK", e);
            }
        }
        if (oracle == null) {
            oracle = new WebDriverWait(driver, 1).until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(
                            By.xpath("//table[contains(@class, 'gssb_c')]/descendant::span[text()='oracle']"));
                }
            });
            oracle.click();
        }
    }

Or do you like this test?
  
    
    /**
     * This is a clean test using page framework.  it has the same function as the test above.  :)
     */
    @Test
    public void autoCompleteUsingXpath() {
        googlePage.autocomplete(Q, "oracle", optionalElement(ORACLE_AUTOCOMPLETE));
    }


That's the difference Selenium Capsules made, and you can see how it works here,
https://github.com/yujunliang/seleniumcapsules/blob/master/src/test/java/com/algocrafts/GoogleAutoCompleteTest.java
 and spring context is here https://github.com/yujunliang/seleniumcapsules/blob/master/src/main/resources/context/pages.xml

more tests to illustrate the cleanness of the code,

1. With anonymous inerclass,

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

2. Without inner class,
    
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
    
            assertEquals(expectedErrorMessages, cartPage.getErrorMessages());
        }

    
3. DatePicker
  
        @Test
        public void pickADate() {
            jQueryDatePickerPage.pick(APRIL, 1, 2012);
            assertEquals("04/01/2012", jQueryDatePickerPage.getDate());
        }
        
4. Lambda Expression,

        public Element locate(AbstractPage page) {
            return Locators.<AbstractPage>element(MAIN_NAV)
                    .and(element(() -> linkText(headText))).locate(page);
        }
    
5. Functional and Stream API, default method in interface and method references.
              
     
     
          default public Stream<Element> images(String fileName) {
              return until(Locators.<Where>elements(IMG)
                           .and(new Filter<>(DISPLAYED.and(SRC.and(new StringContains(fileName)))))
             );
          }
            
          default public Stream<Element> findElements(Supplier<By> by) {
               return findElements(by.get()).stream().map(Element::new);
          }
              
6. Autocomplete example further simplified,

        @Test
        public void autoCompleteUsingSeleniumCapsules1() {
            googlePage.autocomplete("oracle");
        }


Clean, Agile, Simple and Elegant.

http://seleniumcapsules.blogspot.com/

