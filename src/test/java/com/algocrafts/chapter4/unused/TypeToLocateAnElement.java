package com.algocrafts.chapter4.unused;

import com.algocrafts.selenium.Element;

import java.util.Optional;

public interface TypeToLocateAnElement<T> {
    Optional<T> locate(Element element);
}
