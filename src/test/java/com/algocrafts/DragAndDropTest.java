package com.algocrafts;


import com.algocrafts.selenium.Browser;
import org.junit.Ignore;
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

import static com.algocrafts.browsers.Browsers.*;
import static java.util.EnumSet.of;
import static org.openqa.selenium.By.id;

@Ignore
public class DragAndDropTest {

    @Test
    public void testDragAndDrop() {
        for (Browser browser : of(FIREFOX, CHROME, SAFARI)) {
            browser.get("http://www.w3schools.com/html/html5_draganddrop.asp");
            browser.dragAndDrop(id("drag1"), id("div2"));
        }
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void autoCompeleteUsingSelenium() throws InterruptedException {
        FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/Contents/MacOS/firefox-bin"));
        FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
        WebDriver driver = new FirefoxDriver(binary, profile);
        driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement source = driver.findElement(id("drag1"));
        System.out.println(source.getAttribute("src"));
        WebElement target = driver.findElement(id("div2"));
        System.out.println(target.getTagName() + "=" + target.toString());
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target);
        actions.perform();
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void dragAndDropFirefox() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement source = driver.findElement(id("drag1"));
        System.out.println(source.getAttribute("src"));
        WebElement target = driver.findElement(id("div2"));
        System.out.println(target.getTagName() + "=" + target.toString());

        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.clickAndHold(source)
                .moveToElement(target)
                .release(source)
                .build();
        dragAndDrop.perform();
    }

    //This is an ugly test not using page framework, it has the same function as the test below. :(
    @Test
    public void dragAndDropChrome() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement source = driver.findElement(id("drag1"));
        System.out.println(source.getAttribute("src"));
        WebElement target = driver.findElement(id("div2"));
        System.out.println(target.getTagName() + "=" + target.toString());

        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(source)
                .moveToElement(target)
                .release(source)
                .build();
        dragAndDrop.perform();
    }


    @Test
    public void dragAndDropSafari() throws InterruptedException {
        WebDriver driver = new SafariDriver();
        driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement source = driver.findElement(id("drag1"));
        System.out.println(source.getAttribute("src"));
        WebElement target = driver.findElement(id("div2"));
        System.out.println(target.getTagName() + "=" + target.toString());

        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.clickAndHold(source)
                .moveToElement(target)
                .release(source)
                .build();
        dragAndDrop.perform();
    }
}
