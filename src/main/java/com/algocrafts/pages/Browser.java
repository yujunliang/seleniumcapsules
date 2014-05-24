package com.algocrafts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Browser extends Supplier<WebDriver>, WebDriver{

    Stream<Element> getElements(By by);

    void accept();

    void cancel();

    void mouseOver(Element element);

    void frame(int i);

    void defaultContent();

    void save(String title);

    Element findElement(By by);
}
