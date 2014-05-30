seleniumcapsules
================

Selenium Capsules - Encapsulating Selenium

Wrapper of Selenium WebDriver API with Functional Programming feature from Java 8.


http://seleniumcapsules.blogspot.com/

Do you like this test?

        @Test
        public void autoCompeleteUsingSelenium() throws InterruptedException {
            FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/Contents/MacOS/firefox-bin"));
            FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
            WebDriver webDriver = new FirefoxDriver(binary, profile);
            webDriver.get("http://google.com");
            WebElement q = webDriver.findElement(By.name("q"));
            q.clear();
            for (char c : "oracle".toCharArray()) {
                q.sendKeys(String.valueOf(c));
                Thread.sleep(50);
                try {
                    WebElement oracle = webDriver.findElement(
                            By.xpath("//table[contains(@class, 'gssb_c')]/descendant::span[text()='oracle']"));
                    oracle.click();
                } catch (NoSuchElementException e) {
                    log.debug("This is OK", e);
                }
            }
        }

Or do you like this test?
    
        @Test
        public void autoCompleteUsingXpath() {
            googlePage.autocomplete(Q, "oracle", trying(ORACLE_AUTOCOMPLETE));
        }

That's the difference Selenium Capsules made, more tests to illustrate the cleanness of the code,

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
    
    
3. DatePicker,

    
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
    
5. Stream API,


           /**
            * Find the images using the same image file.
            *
            * @param fileName
            * @return the images  using the same image file.
            */
           default public Stream<Element> images(String fileName) {
               return until(Locators.<Where>elements(IMG)
                               .and(new Filter<>(DISPLAYED.and(SRC.and(new StringContains(fileName)))))
               );
           }
       
6. Default method in interface,
      
        
         default public Stream<Element> findElements(Supplier<By> by) {
             return findElements(by.get()).stream().map(Element::new);
         }
          


Clean, Agile, Simple and Elegant.

