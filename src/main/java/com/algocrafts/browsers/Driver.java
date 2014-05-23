package com.algocrafts.browsers;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.function.Supplier;

public interface Driver extends Supplier<WebDriver>{

    File takeScreenShot(WebDriver driver);
}
