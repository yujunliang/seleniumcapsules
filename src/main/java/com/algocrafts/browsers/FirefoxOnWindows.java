package com.algocrafts.browsers;

import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.WebDriverSupplier;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;

/**
 * This class is not one of the enum constants enum Browsers. It is used to demonstrate how to implement the Browser
 * interface thus used as the constructor parameter for AbstractPage. It is declared as a bean in spring context file,
 * <p>
 * &lt;bean id="firefox" class="com.algocrafts.browsers.FirefoxOnWindows"/&gt;
 * <p>
 * and it is referenced by ref attribute of the containing bean.
 * <p>
 * However, the enum browser is referenced by value,
 * &lt;constructor-arg value="${browser}"/&gt;
 * <p>
 * in property file,
 * browser=CHROME
 * <p>
 * The other browsers known to enum don't implement Browser interface, they just implement WebDriverSupplier&lt;T&gt;
 */
public class FirefoxOnWindows implements Browser<FirefoxDriver>, WebDriverSupplier<FirefoxDriver> {

    public FirefoxDriver get() {
        FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/firefox.exe"));
        FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
        return new FirefoxDriver(binary, profile);
    }

    @Override
    public File takeScreenShot(WebDriverSupplier<FirefoxDriver> driver) {
        return driver.get().getScreenshotAs(FILE);
    }

    @Override
    public WebDriverSupplier<FirefoxDriver> getSupplier() {
        return this;
    }

}
