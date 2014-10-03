package com.algocrafts.conditions;

import com.algocrafts.selenium.Element;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by yujunliang on 7/26/14.
 */
public enum OptionalPresents implements Predicate<Optional<Element>>{
    PRESENT;

    @Override
    public boolean test(Optional<Element> elementOptional) {
        return elementOptional.isPresent();
    }
}
