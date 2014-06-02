package com.algocrafts;


import com.algocrafts.pages.Browsers;
import com.algocrafts.selenium.Browser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.util.EnumSet;

import static org.openqa.selenium.By.id;

public class DragAndDropTest {

    @Test
    public void testDragAndDrop() {
        for (Browser browser : EnumSet.of(Browsers.FIREFOX, Browsers.CHROME, Browsers.SAFARI)) {
            browser = Browsers.FIREFOX;
            browser.get("http://www.w3schools.com/html/html5_draganddrop.asp");
            browser.dragAndDrop(id("drag1"), id("div2"));
        }
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void autoCompeleteUsingSelenium() throws InterruptedException {
        FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/Contents/MacOS/firefox-bin"));
        FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
        WebDriver webDriver = new FirefoxDriver(binary, profile);
        webDriver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement source = webDriver.findElement(id("drag1"));
        System.out.println(source.getAttribute("src"));
        WebElement target = webDriver.findElement(id("div2"));
        System.out.println(target.getTagName() + "=" + target.toString());
        Actions actions = new Actions(webDriver);
        actions.dragAndDrop(source, target);
        actions.perform();
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void dragAndDropFirefox() throws InterruptedException {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement source = webDriver.findElement(id("drag1"));
        System.out.println(source.getAttribute("src"));
        WebElement target = webDriver.findElement(id("div2"));
        System.out.println(target.getTagName() + "=" + target.toString());

        Actions builder = new Actions(webDriver);

        Action dragAndDrop = builder.clickAndHold(source)
                .moveToElement(target)
                .release(source)
                .build();
        dragAndDrop.perform();
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void dragAndDropChrome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement source = webDriver.findElement(id("drag1"));
        System.out.println(source.getAttribute("src"));
        WebElement target = webDriver.findElement(id("div2"));
        System.out.println(target.getTagName() + "=" + target.toString());

        Actions builder = new Actions(webDriver);
        Action dragAndDrop = builder.clickAndHold(source)
                .moveToElement(target)
                .release(source)
                .build();
        dragAndDrop.perform();
    }


    @Test
    public void dragAndDropSafari() throws InterruptedException {
        WebDriver webDriver = new SafariDriver();
        webDriver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement source = webDriver.findElement(id("drag1"));
        System.out.println(source.getAttribute("src"));
        WebElement target = webDriver.findElement(id("div2"));
        System.out.println(target.getTagName() + "=" + target.toString());

        Actions builder = new Actions(webDriver);

        Action dragAndDrop = builder.clickAndHold(source)
                .moveToElement(target)
                .release(source)
                .build();
        dragAndDrop.perform();
    }
}
