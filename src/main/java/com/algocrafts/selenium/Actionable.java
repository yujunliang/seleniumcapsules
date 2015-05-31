package com.algocrafts.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.openqa.selenium.OutputType.FILE;

public interface Actionable<T extends WebDriver> extends WebDriver,
        CachedWebDriverSupplier<T> {

    default void accept() {
        try {
            switchTo().alert().accept();
        } catch (Exception e) {
            logger.info("Failed to click OK", e);
        }
    }

    default void cancel() {
        try {
            switchTo().alert().dismiss();
        } catch (Exception e) {
            logger.info("Failed to click Cancel", e);
        }
    }

    default void frame(int i) {
        switchTo().frame(i);
    }

    default void defaultContent() {
        switchTo().defaultContent();
    }

    default void mouseOver(Element element) {
        new Actions(get()).moveToElement(element).perform();
    }

    default void dragAndDrop(By from, By to) {
        new Actions(get()).dragAndDrop(findElement(from), findElement(to)).perform();
    }

    default void save(String title) {
        T driver = get();
        if (driver instanceof TakesScreenshot) {
            logger.info("Saving screenshot [title={}]", title);
            File scrFile = null;
            try {
                TakesScreenshot camera = (TakesScreenshot) driver;
                scrFile = camera.getScreenshotAs(FILE);
                copyFile(scrFile, new File("target/screenshots/" + title + new Date().getTime() + ".png"));
                scrFile.delete();
            } catch (UnhandledAlertException e) {
                logger.info("Failed to copy screenshot.", e);
            } catch (IOException e) {
                try {
                    copyFile(scrFile, new File("target/screenshots/" + new Date().getTime() + ".png"));
                } catch (IOException e1) {
                    logger.info("Failed to copy screenshot.", e1);
                }
            }
        } else {
            logger.info("Taking screenshot is not supported");
        }
    }
}
